package com.letsintern.letsintern.domain.banner.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class BannerCreateNoFileBadRequest extends BaseErrorException {

    public static final BannerCreateNoFileBadRequest EXCEPTION = new BannerCreateNoFileBadRequest();

    private BannerCreateNoFileBadRequest() {
        super(BannerErrorCode.BANNER_CREATE_NO_FILE_BAD_REQUEST);
    }
}
