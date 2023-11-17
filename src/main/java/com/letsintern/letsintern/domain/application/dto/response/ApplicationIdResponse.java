package com.letsintern.letsintern.domain.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationIdResponse {

    private Long applicationId;

    @Builder
    private ApplicationIdResponse(Long applicationId) {
        this.applicationId = applicationId;
    }

    public static ApplicationIdResponse from(Long applicationId) {
        return ApplicationIdResponse.builder()
                .applicationId(applicationId)
                .build();
    }

}
