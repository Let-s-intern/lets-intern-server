package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserApplicationVo {

    private Long id;
    private ApplicationStatus status;
    private String programTitle;
    private Long reviewId;

    @Builder
    public UserApplicationVo(Long id, ApplicationStatus status, String programTitle, Long reviewId) {
        this.id = id;
        this.status = status;
        this.programTitle = programTitle;
        this.reviewId = reviewId;
    }
}
