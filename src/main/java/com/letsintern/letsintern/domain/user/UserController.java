package com.letsintern.letsintern.domain.user;

import com.letsintern.letsintern.domain.user.dto.request.UserExistRequestDTO;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserIdResponseDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserTotalListDTO;
import com.letsintern.letsintern.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/exists")
    public boolean existingUser(@RequestBody UserExistRequestDTO userExistRequestDTO) {
        return userService.existingUser(userExistRequestDTO);
    }

    @PostMapping("/signup")
    public UserIdResponseDTO signUp(@RequestBody UserSignUpDTO userSignUpDTO) {
        return userService.signUp(userSignUpDTO);
    }

    @GetMapping("/list")
    public UserTotalListDTO getUserTotalList() {
        return userService.getUserTotalList();
    }
}
