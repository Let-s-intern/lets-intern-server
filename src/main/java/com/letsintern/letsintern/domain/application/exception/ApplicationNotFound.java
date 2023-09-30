package com.letsintern.letsintern.domain.application.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ApplicationNotFound extends BaseErrorException {

    public static final ApplicationNotFound EXCEPTION = new ApplicationNotFound();


    private ApplicationNotFound() {
        super(ApplicationErrorCode.APPLICATION_NOT_FOUND);
    }
}
