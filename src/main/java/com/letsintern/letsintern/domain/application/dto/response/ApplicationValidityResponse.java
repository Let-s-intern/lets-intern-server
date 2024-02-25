package com.letsintern.letsintern.domain.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplicationValidityResponse {

    private boolean isValid;

    @Builder
    private ApplicationValidityResponse(boolean isValid) {
        this.isValid = isValid;
    }

    public static ApplicationValidityResponse from(boolean isValid) {
        return ApplicationValidityResponse.builder()
                .isValid(isValid)
                .build();
    }
}
