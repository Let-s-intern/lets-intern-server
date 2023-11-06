package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.UserApplication;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserApplicationListResponseDTO {

    private List<UserApplication> userApplicationList;

    @Builder
    private UserApplicationListResponseDTO(List<UserApplication> userApplicationList) {
        this.userApplicationList = userApplicationList;
    }

    public static UserApplicationListResponseDTO from(List<UserApplication> userApplicationList) {
        return UserApplicationListResponseDTO.builder()
                .userApplicationList(userApplicationList)
                .build();
    }
}
