package com.letsintern.letsintern.domain.user.vo;

import com.letsintern.letsintern.global.common.annotation.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UserVo {

    @NotEmpty(message = "이메일을 입력해주세요")
    @ValidEmail
    private String email;

    @NotEmpty(message = "이름을 입력해주세요")
    private String name;

    @NotEmpty(message = "핸드폰 번호를 입력해주세요")
    private String phoneNum;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    private String password;
}
