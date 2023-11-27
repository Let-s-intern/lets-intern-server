package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserApplicationVo {

    private Long id;
    private ApplicationStatus status;
    private String programTitle;

    private ProgramType programType;
    private Long reviewId;

    @Builder
    public UserApplicationVo(Long id, ApplicationStatus status, String programTitle, ProgramType programType, Long reviewId) {
        this.id = id;
        this.status = status;
        this.programTitle = programTitle;
        this.programType = programType;
        this.reviewId = reviewId;
    }
}
