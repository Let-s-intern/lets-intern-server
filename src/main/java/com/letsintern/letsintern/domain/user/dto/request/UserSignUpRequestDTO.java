package com.letsintern.letsintern.domain.user.dto.request;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.letsintern.letsintern.domain.user.vo.UserVo;
import lombok.Getter;

@Getter
public class UserSignUpRequestDTO {

    @JsonUnwrapped private UserVo userVo;
}
