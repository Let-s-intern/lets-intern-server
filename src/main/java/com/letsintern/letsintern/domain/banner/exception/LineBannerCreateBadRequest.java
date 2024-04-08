package com.letsintern.letsintern.domain.banner.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class LineBannerCreateBadRequest extends BaseErrorException {

    public static final LineBannerCreateBadRequest EXCEPTION = new LineBannerCreateBadRequest();

    private LineBannerCreateBadRequest() {
        super(BannerErrorCode.LINE_BANNER_CREATE_BAD_REQUEST);
    }
}
