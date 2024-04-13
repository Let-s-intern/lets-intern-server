package com.letsintern.letsintern.domain.program.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ChallengeNotFoundException extends BaseErrorException {
    public static final ChallengeNotFoundException EXCEPTION = new ChallengeNotFoundException();

    private ChallengeNotFoundException() {
        super(ProgramErrorCode.CHALLENGE_NOT_FOUND);
    }
}
