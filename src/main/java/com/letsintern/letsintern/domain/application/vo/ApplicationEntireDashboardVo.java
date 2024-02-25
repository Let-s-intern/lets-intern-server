package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class ApplicationEntireDashboardVo {

    private Long applicationId;

    private String name;

    private ApplicationWishJob wishJob;

    private String introduction;

    private boolean isMine;

    @Builder
    public ApplicationEntireDashboardVo(Long applicationId, String name, ApplicationWishJob wishJob, String introduction, Long userId, Long applicationUserId) {
        this.applicationId = applicationId;
        this.name = name;
        this.wishJob = wishJob;
        this.introduction = introduction;
        this.isMine = Objects.equals(userId, applicationUserId);
    }
}
