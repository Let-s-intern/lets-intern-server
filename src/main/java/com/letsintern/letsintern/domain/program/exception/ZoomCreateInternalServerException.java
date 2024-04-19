package com.letsintern.letsintern.domain.program.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ZoomCreateInternalServerException extends BaseErrorException {

    public static final ZoomCreateInternalServerException EXCEPTION = new ZoomCreateInternalServerException();

    private ZoomCreateInternalServerException() {
        super(ProgramErrorCode.ZOOM_CREATE_INTERNAL_SERVER);
    }
}