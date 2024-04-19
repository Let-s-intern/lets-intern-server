package com.letsintern.letsintern.domain.coupon.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class CouponExpiredException extends BaseErrorException {
    public static final CouponExpiredException EXCEPTION = new CouponExpiredException();

    private CouponExpiredException() {
        super(CouponErrorCode.COUPON_EXPIRED);
    }
}
