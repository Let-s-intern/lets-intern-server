package com.letsintern.letsintern.domain.application.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ApplicationUnauthorized extends BaseErrorException {

    public static final ApplicationUnauthorized EXCEPTION = new ApplicationUnauthorized();

    private ApplicationUnauthorized() {
        super(ApplicationErrorCode.APPLICATION_UNAUTHORIZED);
    }
}
