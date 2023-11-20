package com.letsintern.letsintern.domain.application.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ApplicationCannotDeleted extends BaseErrorException {

    public static final ApplicationCannotDeleted EXCEPTION = new ApplicationCannotDeleted();

    private ApplicationCannotDeleted() {
        super(ApplicationErrorCode.APPLICATION_CANNOT_DELETED);
    }
}
