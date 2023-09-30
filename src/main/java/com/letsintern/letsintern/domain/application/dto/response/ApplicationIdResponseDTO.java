package com.letsintern.letsintern.domain.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationIdResponseDTO {

    private Long applicationId;

    @Builder
    private ApplicationIdResponseDTO(Long applicationId) {
        this.applicationId = applicationId;
    }

    public static ApplicationIdResponseDTO from(Long applicationId) {
        return ApplicationIdResponseDTO.builder()
                .applicationId(applicationId)
                .build();
    }

}
