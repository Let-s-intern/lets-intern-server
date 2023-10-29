package com.letsintern.letsintern.global.error.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;
import com.letsintern.letsintern.global.error.GlobalErrorCode;

public class ForbiddenUser extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new ForbiddenUser();

    private ForbiddenUser() {
        super(GlobalErrorCode.FORBIDDEN_USER);
    }
}