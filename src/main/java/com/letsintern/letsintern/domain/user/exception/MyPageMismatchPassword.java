package com.letsintern.letsintern.domain.user.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class MyPageMismatchPassword extends BaseErrorException {

    public static final MyPageMismatchPassword EXCEPTION = new MyPageMismatchPassword();

    private MyPageMismatchPassword() {
        super(UserErrorCode.MYPAGE_MISMATCH_PASSWORD);
    }
}
