package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserApplicationVo {

    private Long id;
    private Long reviewId;
    private String programTitle;
    private ApplicationStatus status;

    @Builder
    public UserApplicationVo(Long id, Long reviewId, Boolean isApproved, String programTitle, ProgramStatus programStatus) {
        this.id = id;
        this.reviewId = reviewId;
        this.programTitle = programTitle;

        // 지원 완료
        if(!isApproved && (programStatus.equals(ProgramStatus.OPEN) || programStatus.equals(ProgramStatus.CLOSED))) {
            this.status = ApplicationStatus.APPLIED;
        }

        // 미선발
        else if(!isApproved && (programStatus.equals(ProgramStatus.SELECTED) || programStatus.equals(ProgramStatus.DONE))) {
            this.status = ApplicationStatus.APPLIED_NOT_APPROVED;
        }

        // 참여중 (선발)
        else if(isApproved && programStatus.equals(ProgramStatus.SELECTED)) {
            this.status = ApplicationStatus.IN_PROGRESS;
        }

        // 참여 완료
        else if(isApproved && programStatus.equals(ProgramStatus.DONE)) {
            this.status = ApplicationStatus.DONE;
        }

        // 무효한 케이스 (Program CLOSED && Application isApproved)
        else {
            this.status = ApplicationStatus.APPLIED;
        }
    }
}
