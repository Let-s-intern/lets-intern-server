package com.letsintern.letsintern.domain.user.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class Oauth2DuplicateUser extends BaseErrorException {

    public static final Oauth2DuplicateUser EXCEPTION = new Oauth2DuplicateUser();

    private Oauth2DuplicateUser() {
        super(UserErrorCode.OAUTH2_DUPLICATE_USER);
    }
}
