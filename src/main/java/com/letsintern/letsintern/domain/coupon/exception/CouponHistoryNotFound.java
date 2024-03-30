package com.letsintern.letsintern.domain.coupon.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class CouponHistoryNotFound extends BaseErrorException {

    public static final CouponHistoryNotFound EXCEPTION = new CouponHistoryNotFound();

    private CouponHistoryNotFound() {
        super(CouponErrorCode.COUPON_HISTORY_NOT_FOUND);
    }
}
