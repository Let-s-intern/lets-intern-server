package com.letsintern.letsintern.domain.coupon.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class InvalidCouponProgramType extends BaseErrorException {
    public static final InvalidCouponProgramType EXCEPTION = new InvalidCouponProgramType();

    private InvalidCouponProgramType() {
        super(CouponErrorCode.INVALID_COUPON_PROGRAM_TYPE);
    }
}
