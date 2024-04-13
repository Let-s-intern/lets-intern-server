package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.payment.domain.FeeType;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.user.domain.AccountType;
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

    private FeeType feeType;

    private LocalDateTime programFeeDueDate;

    private AccountType programAccountType;

    private String programAccountNumber;

    private Long reviewId;

    private LocalDateTime announcementDate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Builder
    public ApplicationVo(Long id, ApplicationStatus status, Boolean feeIsConfirmed, Long programId, String programTitle, ProgramType programType,
                         FeeType feeType, LocalDateTime programFeeDueDate, AccountType programAccountType, String programAccountNumber,
                         Long reviewId, LocalDateTime announcementDate, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.status = status;
        this.feeIsConfirmed = feeIsConfirmed;
        this.programId = programId;
        this.programTitle = programTitle;
        this.programType = programType;
        this.feeType = feeType;
        this.programFeeDueDate = programFeeDueDate;
        this.programAccountType = programAccountType;
        this.programAccountNumber = programAccountNumber;
        this.reviewId = reviewId;
        this.announcementDate = announcementDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
