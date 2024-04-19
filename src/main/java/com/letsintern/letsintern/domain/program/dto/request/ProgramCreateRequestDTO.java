package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramFeeType;
import com.letsintern.letsintern.domain.program.domain.ProgramTopic;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import com.letsintern.letsintern.domain.user.domain.AccountType;
import jakarta.annotation.Nullable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProgramCreateRequestDTO {

    private ProgramType type;

    private Integer th;

    private String title;

    private Integer headcount;

    private LocalDateTime dueDate;

    private LocalDateTime announcementDate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String contents;

    private ProgramWay way;

    private String location;

    private String notice;

    private List<Long> faqIdList;


    // 이용료 or 보증금 프로그램
    private ProgramFeeType feeType;

    @Nullable
    private Integer feeRefund;

    @Nullable
    private Integer feeCharge;

    @Nullable
    private LocalDateTime feeDueDate;

    @Nullable
    private AccountType accountType;

    @Nullable
    private String accountNumber;


    // Challenge
    @Nullable
    private ProgramTopic topic;

    @Nullable
    private String openKakaoLink;

    @Nullable
    private String openKakaoPassword;

    // 할인 금액
    @Nullable
    private Integer discountValue;
}
