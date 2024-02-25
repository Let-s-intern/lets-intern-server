package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramFeeType;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApplicationVo {

    private Long id;

    private ApplicationStatus status;

    private Boolean feeIsConfirmed;

    private Long programId;

    private String programTitle;

    private ProgramType programType;

    private ProgramFeeType programFeeType;

    private Long reviewId;

    private LocalDateTime announcementDate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Builder
    public ApplicationVo(Long id, ApplicationStatus status, Boolean feeIsConfirmed, Long programId, String programTitle, ProgramType programType,
                         ProgramFeeType programFeeType, Long reviewId, LocalDateTime announcementDate, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.status = status;
        this.feeIsConfirmed = feeIsConfirmed;
        this.programId = programId;
        this.programTitle = programTitle;
        this.programType = programType;
        this.programFeeType = programFeeType;
        this.reviewId = reviewId;
        this.announcementDate = announcementDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
