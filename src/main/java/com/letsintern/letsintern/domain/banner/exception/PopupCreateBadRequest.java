package com.letsintern.letsintern.domain.banner.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class PopupCreateBadRequest extends BaseErrorException {

    public static final PopupCreateBadRequest EXCEPTION = new PopupCreateBadRequest();

    private PopupCreateBadRequest() {
        super(BannerErrorCode.POPUP_CREATE_BAD_REQUEST);
    }
}
