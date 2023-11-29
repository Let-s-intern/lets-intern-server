package com.letsintern.letsintern.domain.memo.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class MemoNotFound extends BaseErrorException {

    public static final MemoNotFound EXCEPTION = new MemoNotFound();


    private MemoNotFound() {
        super(MemoErrorCode.MEMO_NOT_FOUND);
    }
}
