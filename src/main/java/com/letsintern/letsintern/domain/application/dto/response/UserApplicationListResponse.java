package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.domain.UserApplication;
import com.letsintern.letsintern.domain.application.vo.UserApplicationVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserApplicationListResponse {

    private List<UserApplicationVo> userApplicationList;

    @Builder
    private UserApplicationListResponse(List<UserApplicationVo> userApplicationList) {
        this.userApplicationList = userApplicationList;
    }

    public static UserApplicationListResponse from(List<UserApplicationVo> userApplicationList) {
        return UserApplicationListResponse.builder()
                .userApplicationList(userApplicationList)
                .build();
    }
}
