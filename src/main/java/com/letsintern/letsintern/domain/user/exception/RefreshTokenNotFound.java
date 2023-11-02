package com.letsintern.letsintern.domain.user.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class RefreshTokenNotFound extends BaseErrorException {

    public static final RefreshTokenNotFound EXCEPTION = new RefreshTokenNotFound();

    private RefreshTokenNotFound() {
        super(UserErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}