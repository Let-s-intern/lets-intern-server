package com.letsintern.letsintern.global.error.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;
import com.letsintern.letsintern.global.error.GlobalErrorCode;

public class NoTokenException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new NoTokenException();

    private NoTokenException() {
        super(GlobalErrorCode.NO_TOKEN);
    }
}
