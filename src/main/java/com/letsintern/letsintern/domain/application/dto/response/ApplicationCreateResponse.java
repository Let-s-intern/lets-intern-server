package com.letsintern.letsintern.domain.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationCreateResponse {

    private Long applicationId;
    private String announcementDate;

    @Builder
    private ApplicationCreateResponse(Long applicationId, String announcementDate) {
        this.applicationId = applicationId;
        this.announcementDate = announcementDate;
    }

    public static ApplicationCreateResponse from(Long applicationId, String announcementDate) {
        return ApplicationCreateResponse.builder()
                .applicationId(applicationId)
                .announcementDate(announcementDate)
                .build();
    }
}
