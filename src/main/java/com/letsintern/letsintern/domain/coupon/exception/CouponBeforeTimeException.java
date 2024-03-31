package com.letsintern.letsintern.domain.coupon.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class CouponBeforeTimeException extends BaseErrorException {
    public static CouponBeforeTimeException EXCEPTION = new CouponBeforeTimeException();
    private CouponBeforeTimeException() {
        super(CouponErrorCode.COUPON_BEFORE_TIME);
    }
}
