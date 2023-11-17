package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.domain.UserApplication;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserApplicationListResponse {

    private List<UserApplication> userApplicationList;

    @Builder
    private UserApplicationListResponse(List<UserApplication> userApplicationList) {
        this.userApplicationList = userApplicationList;
    }

    public static UserApplicationListResponse from(List<UserApplication> userApplicationList) {
        return UserApplicationListResponse.builder()
                .userApplicationList(userApplicationList)
                .build();
    }
}
