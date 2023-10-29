package com.letsintern.letsintern.domain.user;

import com.letsintern.letsintern.domain.user.dto.request.UserSignInRequest;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpRequest;
import com.letsintern.letsintern.domain.user.dto.response.TokenResponse;
import com.letsintern.letsintern.domain.user.dto.response.UserIdResponseDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserTotalListDTO;
import com.letsintern.letsintern.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public UserIdResponseDTO signUp(@RequestBody @Valid UserSignUpRequest signUpRequest) {
        return userService.signUp(signUpRequest);
    }

    @Operation(summary = "로그인")
    @PostMapping("/signin")
    public TokenResponse signIn(@RequestBody @Valid UserSignInRequest signInRequest) {
        return userService.signIn(signInRequest);
    }

    @Operation(summary = "유저 전체 목록")
    @GetMapping("/list")
    public UserTotalListDTO getUserTotalList() {
        return userService.getUserTotalList();
    }
}
