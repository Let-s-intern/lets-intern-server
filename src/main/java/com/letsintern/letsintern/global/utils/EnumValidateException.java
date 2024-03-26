package com.letsintern.letsintern.global.utils;

import com.letsintern.letsintern.global.error.BaseErrorException;
import com.letsintern.letsintern.global.error.GlobalErrorCode;

public class EnumValidateException extends BaseErrorException {
    public static final BaseErrorException EXCEPTION = new EnumValidateException();

    public EnumValidateException() {
        super(GlobalErrorCode.INVALID_ENUM_CODE);
    }

}
