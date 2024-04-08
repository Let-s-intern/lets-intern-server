package com.letsintern.letsintern.domain.banner.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class MainOrProgramBannerCreateBadRequest extends BaseErrorException {

    public static final MainOrProgramBannerCreateBadRequest EXCEPTION = new MainOrProgramBannerCreateBadRequest();

    private MainOrProgramBannerCreateBadRequest() {
        super(BannerErrorCode.MAIN_OR_PROGRAM_BANNER_CREATE_BAD_REQUEST);
    }
}
