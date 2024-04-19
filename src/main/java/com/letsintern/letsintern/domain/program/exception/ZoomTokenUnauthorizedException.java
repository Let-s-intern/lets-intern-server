package com.letsintern.letsintern.domain.program.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ZoomTokenUnauthorizedException extends BaseErrorException {

    public static final ZoomTokenUnauthorizedException EXCEPTION = new ZoomTokenUnauthorizedException();

    private ZoomTokenUnauthorizedException() {
        super(ProgramErrorCode.ZOOM_CREATE_TOKEN_ERROR);
    }
}
