package com.letsintern.letsintern.domain.application.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ApplicationGuestBadRequest extends BaseErrorException {

    public static final ApplicationGuestBadRequest EXCEPTION = new ApplicationGuestBadRequest();

    private ApplicationGuestBadRequest() {
        super(ApplicationErrorCode.APPLICATION_GUEST_BAD_REQUEST);
    }
}
