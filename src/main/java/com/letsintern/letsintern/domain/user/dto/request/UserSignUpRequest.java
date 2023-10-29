package com.letsintern.letsintern.domain.user.dto.request;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.letsintern.letsintern.domain.user.vo.UserVo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class UserSignUpRequest {

    @JsonUnwrapped private UserVo userVo;
}
