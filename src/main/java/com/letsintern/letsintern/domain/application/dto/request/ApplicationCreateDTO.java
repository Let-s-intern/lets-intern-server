package com.letsintern.letsintern.domain.application.dto.request;

import com.letsintern.letsintern.domain.application.domain.InflowPath;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationCreateDTO {

    @NotNull
    private Integer grade;

    @NotNull
    private String wishCompany;

    @NotNull
    private String wishJob;

    @NotNull
    private String applyMotive;

    @Nullable
    private String preQuestions;

    @NotNull
    private InflowPath inflowPath;


    /* 회원 추가 정보 */
    @Nullable
    private String university;

    @Nullable
    private String major;


    /* 비회원 추가 정보 */

    @Nullable
    private String guestName;

    @Nullable
    private String guestPhoneNum;

    @Nullable
    private String guestEmail;

}
