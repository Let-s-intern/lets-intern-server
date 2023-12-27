package com.letsintern.letsintern.domain.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class ApplicationCreateResponse {

    private Long applicationId;
    private LocalDateTime announcementDate;

    @Builder
    private ApplicationCreateResponse(Long applicationId, LocalDateTime announcementDate) {
        this.applicationId = applicationId;
        this.announcementDate = announcementDate;
    }

    public static ApplicationCreateResponse from(Long applicationId, LocalDateTime announcementDate) {
        return ApplicationCreateResponse.builder()
                .applicationId(applicationId)
                .announcementDate(announcementDate)
                .build();
    }
}
