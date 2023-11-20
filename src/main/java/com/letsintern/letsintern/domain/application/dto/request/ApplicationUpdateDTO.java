package com.letsintern.letsintern.domain.application.dto.request;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationUpdateDTO {

    private Boolean isApproved;

    @Nullable
    private Integer grade;

    @Nullable
    private String wishCompany;

    @Nullable
    private String wishJob;

    @Nullable
    private String applyMotive;

    @Nullable
    private Boolean attendance;


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
