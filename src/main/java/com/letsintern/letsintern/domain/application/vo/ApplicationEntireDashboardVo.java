package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplicationEntireDashboardVo {

    private Long applicationId;

    private String name;

    private String wishJob;

    private String introduction;

    @Builder
    public ApplicationEntireDashboardVo(Long applicationId, String name, ApplicationWishJob wishJob, String introduction) {
        this.applicationId = applicationId;
        this.name = name;
        this.wishJob = wishJob.getValue();
        this.introduction = introduction;
    }
}
