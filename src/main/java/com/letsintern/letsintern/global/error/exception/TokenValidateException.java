package com.letsintern.letsintern.global.error.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;
import com.letsintern.letsintern.global.error.GlobalErrorCode;

public class TokenValidateException extends BaseErrorException {

    public static final BaseErrorException EXCEPTION = new TokenValidateException();

    public TokenValidateException() {
        super(GlobalErrorCode.INVALID_AUTH_TOKEN);
    }
}
