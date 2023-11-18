package com.letsintern.letsintern.domain.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserSignInRequestDTO {

    @Schema(defaultValue = "string")
    @NotNull(message = "이메일을 입력해주세요")
    private String email;

    @Schema(defaultValue = "string")
    @NotNull(message = "비밀번호를 입력해주세요")
    private String password;

}
