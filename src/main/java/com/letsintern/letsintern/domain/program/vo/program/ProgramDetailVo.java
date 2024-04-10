package com.letsintern.letsintern.domain.program.vo.program;

import com.letsintern.letsintern.domain.payment.domain.FeeType;
import com.letsintern.letsintern.domain.program.domain.ChallengeTopic;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import com.letsintern.letsintern.domain.user.domain.AccountType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProgramDetailVo(
        Long programId,
        ProgramStatus status,
        String title,
        String contents,
        String notice,
        ProgramWay way,
        String location,
        LocalDateTime dueDate,
        LocalDateTime announcementDate,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime feeDueDate,
        FeeType feeType,
        Integer feeRefund,
        Integer feeCharge,
        Integer discountValue,
        AccountType accountType,
        String accountNumber,
        String faqListStr,
        ProgramType programType
) {
}
