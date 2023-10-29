package com.letsintern.letsintern.domain.user.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class DuplicateUser extends BaseErrorException {

    public static final DuplicateUser EXCEPTION = new DuplicateUser();

    private DuplicateUser() {
        super(UserErrorCode.DUPLICATE_USER);
    }
}
