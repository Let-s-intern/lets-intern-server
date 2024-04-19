package com.letsintern.letsintern.domain.coupon.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class CouponNotFound extends BaseErrorException {

    public static final CouponNotFound EXCEPTION = new CouponNotFound();

    private CouponNotFound() {
        super(CouponErrorCode.COUPON_NOT_FOUND);
    }
}

