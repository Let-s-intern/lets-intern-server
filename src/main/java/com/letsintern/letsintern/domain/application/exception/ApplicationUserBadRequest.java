package com.letsintern.letsintern.domain.application.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ApplicationUserBadRequest extends BaseErrorException {

    public static final ApplicationUserBadRequest EXCEPTION = new ApplicationUserBadRequest();

    private ApplicationUserBadRequest() {
        super(ApplicationErrorCode.APPLICATION_USER_BAD_REQUEST);
    }
}
