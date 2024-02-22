package com.letsintern.letsintern.domain.application.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ApplicationUserBadRequestAccount extends BaseErrorException {

    public static final ApplicationUserBadRequestAccount EXCEPTION = new ApplicationUserBadRequestAccount();

    private ApplicationUserBadRequestAccount() {
        super(ApplicationErrorCode.APPLICATION_USER_BAD_REQUEST_ACCOUNT);
    }
}
