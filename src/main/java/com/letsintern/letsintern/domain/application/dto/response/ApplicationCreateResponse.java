package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.application.domain.Application;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    public static ApplicationCreateResponse from(Application application) {
        return ApplicationCreateResponse.builder()
                .applicationId(application.getId())
                .announcementDate(application.getProgram().getAnnouncementDate())
                .build();
    }
}
