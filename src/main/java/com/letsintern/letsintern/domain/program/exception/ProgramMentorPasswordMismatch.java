package com.letsintern.letsintern.domain.program.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ProgramMentorPasswordMismatch extends BaseErrorException {

    public static final ProgramMentorPasswordMismatch EXCEPTION = new ProgramMentorPasswordMismatch();

    private ProgramMentorPasswordMismatch() {
        super(ProgramErrorCode.PROGRAM_MENTOR_PASSWORD_MISMATCH);
    }
}
