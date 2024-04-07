package com.letsintern.letsintern.domain.banner.domain.popup.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class PopupCreateBadRequest extends BaseErrorException {

    public static final PopupCreateBadRequest EXCEPTION = new PopupCreateBadRequest();

    private PopupCreateBadRequest() {
        super(PopupErrorCode.POPUP_CREATE_BAD_REQUEST);
    }
}
