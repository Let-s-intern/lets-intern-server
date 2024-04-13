package com.letsintern.letsintern.domain.payment.helper;

import com.letsintern.letsintern.domain.payment.domain.FeeType;
import com.letsintern.letsintern.domain.payment.dto.request.PaymentRequestDto;
import com.letsintern.letsintern.domain.payment.exception.ChargeProgramCreateBadRequest;
import com.letsintern.letsintern.domain.payment.exception.RefundProgramCreateBadRequest;
import com.letsintern.letsintern.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.letsintern.letsintern.global.utils.EnumValueUtils.toEntityCode;

@RequiredArgsConstructor
@Component
public class PaymentHelper {
    private final PaymentRepository paymentRepository;

    public void validatePaymentProgramInput(PaymentRequestDto requestDto) {
        if (Objects.isNull(requestDto)) return;
        if (FeeType.REFUND.equals(toEntityCode(FeeType.class, requestDto.feeType())))
            validateRefundTypeProgramInput(requestDto);
        if (FeeType.CHARGE.equals(toEntityCode(FeeType.class, requestDto.feeType())))
            validateChargeTypeProgramInput(requestDto);
    }

    private void validateChargeTypeProgramInput(PaymentRequestDto paymentRequestDto) {
        if (Objects.isNull(paymentRequestDto.feeType())
                || Objects.isNull(paymentRequestDto.accountType())
                || Objects.isNull(paymentRequestDto.accountNumber())
                || Objects.isNull(paymentRequestDto.feeDueDate())) {
            throw ChargeProgramCreateBadRequest.EXCEPTION;
        }
    }

    private void validateRefundTypeProgramInput(PaymentRequestDto paymentRequestDto) {
        if (Objects.isNull(paymentRequestDto.feeRefund())
                || Objects.isNull(paymentRequestDto.feeCharge())
                || Objects.isNull(paymentRequestDto.accountType())
                || Objects.isNull(paymentRequestDto.accountNumber())
                || Objects.isNull(paymentRequestDto.feeDueDate())) {
            throw RefundProgramCreateBadRequest.EXCEPTION;
        }
    }

}
