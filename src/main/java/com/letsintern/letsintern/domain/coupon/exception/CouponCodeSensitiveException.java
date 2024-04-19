package com.letsintern.letsintern.domain.coupon.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class CouponCodeSensitiveException extends BaseErrorException {
    public static CouponCodeSensitiveException EXCEPTION = new CouponCodeSensitiveException();

    private CouponCodeSensitiveException() {
        super(CouponErrorCode.SENSITIVE_COUPON_CODE);
    }
}
