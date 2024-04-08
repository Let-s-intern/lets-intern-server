package com.letsintern.letsintern.domain.coupon.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class DuplicateCouponCode extends BaseErrorException {
    public static final DuplicateCouponCode EXCEPTION = new DuplicateCouponCode();

    private DuplicateCouponCode() {
        super(CouponErrorCode.DUPLICATE_COUPON_CODE);
    }
}