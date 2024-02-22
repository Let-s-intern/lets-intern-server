package com.letsintern.letsintern.domain.program.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class RefundProgramCreateBadRequest extends BaseErrorException {

    public static final RefundProgramCreateBadRequest EXCEPTION = new RefundProgramCreateBadRequest();

    private RefundProgramCreateBadRequest() {
        super(ProgramErrorCode.PROGRAM_CREATE_BAD_REQUEST_REFUND);
    }
}
