package com.letsintern.letsintern.domain.payment.dto.request;

import com.letsintern.letsintern.domain.payment.domain.FeeType;
import com.letsintern.letsintern.domain.user.domain.AccountType;

import java.time.LocalDateTime;

public record PaymentRequestDto(
        Integer feeType,
        Integer feeRefund,
        Integer feeCharge,
        Integer discountValue,
        LocalDateTime feeDueDate,
        AccountType accountType,
        String accountNumber
) {
}
