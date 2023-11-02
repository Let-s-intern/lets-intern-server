package com.letsintern.letsintern.domain.user.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class NotRefreshToken extends BaseErrorException {

    public static final NotRefreshToken EXCEPTION = new NotRefreshToken();

    public NotRefreshToken() {
        super(UserErrorCode.NOT_REFRESH_TOKEN);
    }
}
