package com.letsintern.letsintern.domain.payment.helper;

import com.letsintern.letsintern.domain.payment.domail.Payment;
import com.letsintern.letsintern.domain.payment.domail.ProgramFeeType;
import com.letsintern.letsintern.domain.payment.dto.request.PaymentRequestDto;
import com.letsintern.letsintern.domain.payment.exception.ChargeProgramCreateBadRequest;
import com.letsintern.letsintern.domain.payment.exception.RefundProgramCreateBadRequest;
import com.letsintern.letsintern.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class PaymentHelper {
    private final PaymentRepository paymentRepository;

    public Payment findPaymentFromProgramIdOrThrow(Long programId) {
        
    }

    public void validatePaymentProgramInput(PaymentRequestDto requestDto) {
        if (Objects.isNull(requestDto)) return;
        if (ProgramFeeType.REFUND.equals(requestDto.programFeeType()))
            validateRefundTypeProgramInput(requestDto);
        if (ProgramFeeType.CHARGE.equals(requestDto.programFeeType()))
            validateChargeTypeProgramInput(requestDto);
    }

    private void validateChargeTypeProgramInput(PaymentRequestDto paymentRequestDto) {
        if (Objects.isNull(paymentRequestDto.programFeeType())
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
