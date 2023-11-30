package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.program.vo.UserProgramVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UserProgramVoResponse {

    private List<UserProgramVo> userProgramList;

    @Builder
    private UserProgramVoResponse(List<UserProgramVo> userProgramList) {
        this.userProgramList = userProgramList;
    }

    public static UserProgramVoResponse from(List<UserProgramVo> userProgramList) {
        return UserProgramVoResponse.builder()
                .userProgramList(userProgramList)
                .build();
    }
}
