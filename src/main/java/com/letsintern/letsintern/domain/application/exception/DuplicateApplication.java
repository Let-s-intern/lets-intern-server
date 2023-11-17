package com.letsintern.letsintern.domain.application.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class DuplicateApplication extends BaseErrorException {

    public static final DuplicateApplication EXCEPTION = new DuplicateApplication();

    private DuplicateApplication() {
        super(ApplicationErrorCode.DUPLICATE_APPLICATION);
    }
}
