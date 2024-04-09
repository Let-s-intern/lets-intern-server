package com.letsintern.letsintern.domain.payment.exception;

import com.letsintern.letsintern.global.common.dto.ErrorReason;
import com.letsintern.letsintern.global.error.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum PaymentErrorCode implements BaseErrorCode {
    PROGRAM_CREATE_BAD_REQUEST_CHARGE(BAD_REQUEST, "PROGRAM_400_1", "이용료 프로그램 개설은 feeCharge, accountType, accountNumber, feeDueDate 정보가 모두 필요합니다"),
    PROGRAM_CREATE_BAD_REQUEST_REFUND(BAD_REQUEST, "PROGRAM_400_2", "보증금 프로그램 개설은 feeRefund, feeCharge, accountType, accountNumber, feeDueDate 정보가 모두 필요합니다");

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.of(status.value(), code, reason);
    }
}
