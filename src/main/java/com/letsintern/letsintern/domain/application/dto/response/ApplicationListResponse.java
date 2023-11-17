package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.domain.Application;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ApplicationListResponse {

    private List<Application> applicationList;

    @Builder
    private ApplicationListResponse(List<Application> applicationList) {
        this.applicationList = applicationList;
    }

    public static ApplicationListResponse from(List<Application> applicationList) {
        return ApplicationListResponse.builder()
                .applicationList(applicationList)
                .build();
    }
}
