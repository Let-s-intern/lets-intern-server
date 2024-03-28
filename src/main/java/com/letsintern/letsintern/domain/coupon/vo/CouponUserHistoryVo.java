package com.letsintern.letsintern.domain.coupon.vo;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CouponUserHistoryVo(
        Coupon coupon,
        User user,
        Integer remainTime
) {
    public static CouponUserHistoryVo of(Coupon coupon, Integer remainTime) {
        return CouponUserHistoryVo.builder()
                .coupon(coupon)
                .user(null)
                .remainTime(remainTime)
                .build();
    }
}
