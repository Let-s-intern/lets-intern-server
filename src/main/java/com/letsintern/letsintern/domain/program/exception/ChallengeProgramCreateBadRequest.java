package com.letsintern.letsintern.domain.program.exception;

import com.letsintern.letsintern.domain.payment.exception.PaymentErrorCode;
import com.letsintern.letsintern.global.error.BaseErrorException;

public class ChallengeProgramCreateBadRequest extends BaseErrorException {

    public static final ChallengeProgramCreateBadRequest EXCEPTION = new ChallengeProgramCreateBadRequest();

    private ChallengeProgramCreateBadRequest() {
        super(ProgramErrorCode.PROGRAM_CREATE_BAD_REQUEST_CHALLENGE);
    }
}
