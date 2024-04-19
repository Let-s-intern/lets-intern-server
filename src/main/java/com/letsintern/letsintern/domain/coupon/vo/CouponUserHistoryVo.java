package com.letsintern.letsintern.domain.coupon.vo;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.Builder;

@Builder
public record CouponUserHistoryVo(
        Coupon coupon,
        User user,
        Integer remainTime
) {
    public CouponUserHistoryVo(Coupon coupon, User user, Integer remainTime) {
        this.coupon = coupon;
        this.user = user;
        this.remainTime = remainTime;
    }

    public static CouponUserHistoryVo of(Coupon coupon, Integer remainTime) {
        return CouponUserHistoryVo.builder()
                .coupon(coupon)
                .user(null)
                .remainTime(remainTime)
                .build();
    }
}
