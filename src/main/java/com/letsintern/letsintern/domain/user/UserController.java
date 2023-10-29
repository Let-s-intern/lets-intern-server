package com.letsintern.letsintern.domain.user;

import com.letsintern.letsintern.domain.user.dto.request.UserExistRequestDTO;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserIdResponseDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserTotalListDTO;
import com.letsintern.letsintern.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @Operation(summary = "기존에 가입된 유저인지 여부 확인")
    @GetMapping("/exists")
    public boolean existingUser(@RequestBody UserExistRequestDTO userExistRequestDTO) {
        return userService.existingUser(userExistRequestDTO);
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public UserIdResponseDTO signUp(@RequestBody UserSignUpDTO userSignUpDTO) {
        return userService.signUp(userSignUpDTO);
    }

    @Operation(summary = "유저 전체 목록")
    @GetMapping("/list")
    public UserTotalListDTO getUserTotalList() {
        return userService.getUserTotalList();
    }
}
