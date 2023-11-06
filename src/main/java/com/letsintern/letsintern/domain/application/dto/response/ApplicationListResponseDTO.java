package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.domain.UserApplication;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ApplicationListResponseDTO {

    private List<UserApplication> userApplicationList;

    @Builder
    private ApplicationListResponseDTO(List<UserApplication> userApplicationList) {
        this.userApplicationList = userApplicationList;
    }

    public static ApplicationListResponseDTO from(List<UserApplication> userApplicationList) {
        return ApplicationListResponseDTO.builder()
                .userApplicationList(userApplicationList)
                .build();
    }
}
