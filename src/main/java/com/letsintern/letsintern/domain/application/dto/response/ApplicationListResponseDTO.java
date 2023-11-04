package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.domain.Application;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ApplicationListResponseDTO {

    private List<Application> applicationList;

    @Builder
    private ApplicationListResponseDTO(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    public static ApplicationListResponseDTO from(List<Application> applicationList) {
        return ApplicationListResponseDTO.builder()
                .applicationList(applicationList)
                .build();
    }
}
