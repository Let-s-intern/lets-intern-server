package com.letsintern.letsintern.domain.program.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ProgramNotFound extends BaseErrorException {

    public static final ProgramNotFound EXCEPTION = new ProgramNotFound();


    private ProgramNotFound() {
        super(ProgramErrorCode.PROGRAM_NOT_FOUND);
    }
}
