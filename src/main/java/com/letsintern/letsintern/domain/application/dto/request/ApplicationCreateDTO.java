package com.letsintern.letsintern.domain.application.dto.request;

import com.letsintern.letsintern.domain.application.domain.ApplicationWay;
import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.domain.InflowPath;
import com.letsintern.letsintern.domain.user.domain.AccountType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationCreateDTO {

    @NotNull
    private Integer grade;

    @NotNull
    private String wishCompany;

    @NotNull
    private ApplicationWishJob wishJob;

    @NotNull
    private String applyMotive;

    @Nullable
    private String preQuestions;

    @Nullable
    private ApplicationWay way;

    @NotNull
    private InflowPath inflowPath;


    /* 회원 추가 정보 */
    @Nullable
    private String university;

    @Nullable
    private String major;

    @Nullable
    private AccountType accountType;

    @Nullable
    private String accountNumber;


    /* 비회원 추가 정보 */

    @Nullable
    private String guestName;

    @Nullable
    private String guestPhoneNum;

    @Nullable
    private String guestEmail;

    /* 쿠폰 추가 내용 */
    @Nullable
    private String code;
}
