package com.letsintern.letsintern.domain.coupon.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class CouponUsageLimitExceededException extends BaseErrorException {
    public static final CouponUsageLimitExceededException EXCEPTION = new CouponUsageLimitExceededException();

    private CouponUsageLimitExceededException() {
        super(CouponErrorCode.COUPON_USAGE_LIMIT_EXCEEDED);
    }
}
