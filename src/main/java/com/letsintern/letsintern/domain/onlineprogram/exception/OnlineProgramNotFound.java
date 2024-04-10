package com.letsintern.letsintern.domain.onlineprogram.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class OnlineProgramNotFound extends BaseErrorException {

    public static final OnlineProgramNotFound EXCEPTION = new OnlineProgramNotFound();

    private OnlineProgramNotFound() {
        super(OnlineProgramErrorCode.ONLINE_PROGRAM_NOT_FOUND);
    }
}
