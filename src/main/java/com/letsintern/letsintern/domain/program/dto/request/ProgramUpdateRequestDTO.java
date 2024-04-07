package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.payment.domail.ProgramFeeType;
import com.letsintern.letsintern.domain.program.domain.*;
import com.letsintern.letsintern.domain.user.domain.AccountType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProgramUpdateRequestDTO {

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

    private ProgramStatus status;

    private Boolean isVisible;

    private List<Long> faqIdList;


    private String link;

    private String linkPassword;

    private ProgramFeeType feeType;

    private Integer feeRefund;

    private Integer feeCharge;

    private Integer discountValue;

    private LocalDateTime feeDueDate;

    private AccountType accountType;

    private String accountNumber;


    private ProgramTopic topic;

    private String openKakaoLink;

    private String openKakaoPassword;
}
