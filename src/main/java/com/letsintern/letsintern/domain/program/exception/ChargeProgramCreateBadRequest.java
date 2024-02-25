package com.letsintern.letsintern.domain.program.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ChargeProgramCreateBadRequest extends BaseErrorException {

    public static final ChargeProgramCreateBadRequest EXCEPTION = new ChargeProgramCreateBadRequest();

    private ChargeProgramCreateBadRequest() {
        super(ProgramErrorCode.PROGRAM_CREATE_BAD_REQUEST_CHARGE);
    }
}
