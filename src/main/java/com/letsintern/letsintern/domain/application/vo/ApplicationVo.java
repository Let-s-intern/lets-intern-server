package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplicationVo {

    private Long id;
    private ApplicationStatus status;

    private Long programId;
    private String programTitle;

    private ProgramType programType;
    private Long reviewId;

    @Builder
    public ApplicationVo(Long id, ApplicationStatus status, Long programId, String programTitle, ProgramType programType, Long reviewId) {
        this.id = id;
        this.status = status;
        this.programId = programId;
        this.programTitle = programTitle;
        this.programType = programType;
        this.reviewId = reviewId;
    }
}
