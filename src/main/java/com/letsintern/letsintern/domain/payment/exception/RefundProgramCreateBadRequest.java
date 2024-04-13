package com.letsintern.letsintern.domain.payment.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class RefundProgramCreateBadRequest extends BaseErrorException {

    public static final RefundProgramCreateBadRequest EXCEPTION = new RefundProgramCreateBadRequest();

    private RefundProgramCreateBadRequest() {
        super(PaymentErrorCode.PROGRAM_CREATE_BAD_REQUEST_REFUND);
    }
}
