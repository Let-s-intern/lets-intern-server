package com.letsintern.letsintern.domain.user.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class MismatchPassword extends BaseErrorException {

    public static final MismatchPassword EXCEPTION = new MismatchPassword();

    private MismatchPassword() {
        super(UserErrorCode.MISMATCH_PASSWORD);
    }
}
