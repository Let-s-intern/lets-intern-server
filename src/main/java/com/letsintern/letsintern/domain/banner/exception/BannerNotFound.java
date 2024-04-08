package com.letsintern.letsintern.domain.banner.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class BannerNotFound extends BaseErrorException {

    public static final BannerNotFound EXCEPTION = new BannerNotFound();

    private BannerNotFound() {
        super(BannerErrorCode.BANNER_NOT_FOUND);
    }
}
