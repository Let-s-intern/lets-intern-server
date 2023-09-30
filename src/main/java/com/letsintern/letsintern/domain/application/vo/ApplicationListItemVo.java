package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.Application;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplicationListItemVo {

    private Long id;

    private String applyMotive;

    private String question;

    private Boolean checkAttendance;

    private Long programId;

    private Long userId;

    @Builder
    private ApplicationListItemVo(Long id, String applyMotive, String question,
                                  Boolean checkAttendance, Long programId, Long userId) {
        this.id = id;
        this.applyMotive = applyMotive;
        this.question = question;
        this.checkAttendance = checkAttendance;
        this.programId = programId;
        this.userId = userId;
    }

    public static ApplicationListItemVo from(Application application) {
        return ApplicationListItemVo.builder()
                .id(application.getId())
                .applyMotive(application.getApplyMotive())
                .question(application.getQuestion())
                .checkAttendance(application.getCheckAttendance())
                .programId(application.getProgram().getId())
                .userId(application.getUser().getId())
                .build();
    }
}
