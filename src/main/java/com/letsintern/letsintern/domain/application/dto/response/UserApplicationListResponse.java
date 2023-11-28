package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.vo.ApplicationVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserApplicationListResponse {

    private List<ApplicationVo> userApplicationList;

    @Builder
    private UserApplicationListResponse(List<ApplicationVo> userApplicationList) {
        this.userApplicationList = userApplicationList;
    }

    public static UserApplicationListResponse from(List<ApplicationVo> userApplicationList) {
        return UserApplicationListResponse.builder()
                .userApplicationList(userApplicationList)
                .build();
    }
}
