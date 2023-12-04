package com.letsintern.letsintern.domain.user;

import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.dto.request.*;
import com.letsintern.letsintern.domain.user.dto.response.*;
import com.letsintern.letsintern.domain.user.service.UserService;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public UserIdResponseDTO signUp(@RequestBody @Valid UserSignUpRequestDTO signUpRequest) {
        return userService.signUp(signUpRequest);
    }

    @Operation(summary = "로그인")
    @PostMapping("/signin")
    public TokenResponse signIn(@RequestBody @Valid UserSignInRequestDTO signInRequest) {
        return userService.signIn(signInRequest);
    }

    @Operation(summary = "로그아웃")
    @GetMapping("/signout")
    public void signOut(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.signOut(principalDetails);
    }

    @Operation(summary = "토큰 재발급")
    @PostMapping("/reissue")
    public TokenResponse reissueToken(@RequestBody TokenRequestDTO tokenRequestDTO) {
        return userService.reissueToken(tokenRequestDTO);
    }

    @Operation(summary = "마이페이지 비밀번호 변경")
    @PatchMapping("/password")
    public void changePw(@AuthenticationPrincipal PrincipalDetails principalDetails,
                         @RequestBody PwChangeDTO pwChangeDTO) {
        userService.changePassword(principalDetails, pwChangeDTO);
    }

    @Operation(summary = "비밀번호 재설정 메일 보내기")
    @PostMapping("/password")
    public void sendPwResetMail(@RequestBody PwResetMailDTO pwResetMailDTO) {
        userService.sendPwResetMail(pwResetMailDTO);
    }

    @Operation(summary = "회원 탈퇴")
    @GetMapping("/withdraw")
    public void withdraw(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.withdraw(principalDetails);
    }

    @Operation(summary = "지원자 추가 정보 유무 확인")
    @GetMapping("/detail-info")
    public ResponseEntity<Boolean> checkDetailInfoExist(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(userService.checkDetailInfoExist(principalDetails));
    }

    @Operation(summary = "관리자 여부 확인")
    @GetMapping("/isAdmin")
    public ResponseEntity<Boolean> isAdmin(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.ok(userService.checkIsAdmin(principalDetails));
    }

    @Operation(summary = "마이페이지 사용자 정보")
    @GetMapping("")
    public UserInfoResponseDTO getUserInfo(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return userService.getUserInfo(principalDetails);
    }

    @Operation(summary = "마이페이지 사용자 정보 수정")
    @PatchMapping("")
    public UserIdResponseDTO updateUserInfo(
            @RequestBody UserUpdateRequestDTO userUpdateRequestDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return userService.updateUserInfo(userUpdateRequestDTO, principalDetails);
    }

    @Operation(summary = "어드민 사용자 전체 목록")
    @GetMapping("/admin")
    public AdminUserListResponseDTO getAdminUserTotalList(@PageableDefault(size = 20) Pageable pageable) {
        return userService.getAdminUserTotalList(pageable);
    }

    @Operation(summary = "어드민 사용자 1명 정보")
    @GetMapping("/admin/{userId}")
    public ResponseEntity<User> getAdminUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getAdminUser(userId));
    }

//    @Operation(summary = "어드민 사용자 1명 수정")
//    @PutMapping("/admin/{userId}")
//    public UserIdResponseDTO updateAdminUser(@PathVariable Long userId, @RequestBody User user) {
//        return userService.updateAdminUser(userId, user);
//    }



    @Operation(summary = "어드민 사용자 검색 (name, email, phoneNum)")
    @GetMapping("/admin/search")
    public AdminUserListResponseDTO getAdminUserList(@RequestParam String type, @RequestParam String keyword) {
        return userService.getAdminUserList(type, keyword);
    }

    @Operation(summary = "어드민 매니저 목록")
    @GetMapping("/admin/manager")
    public AdminManagerListResponse getAdminManagerList() {
        return userService.getAdminManagerList();
    }

    @Operation(summary = "어드민 유저 담당 매니저 설정")
    @GetMapping ("/admin/{userId}/{managerId}")
    public UserIdResponseDTO setUserManager(@PathVariable Long userId, @PathVariable Long managerId) {
        return userService.setUserManager(userId, managerId);
    }
}
