package com.letsintern.letsintern.domain.coupon.vo;

import java.time.LocalDateTime;

public record CouponUserHistoryVo(
        Long couponId,
        Long userId,
        Integer discount,
        Integer remainTime,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
    public CouponUserHistoryVo(Long couponId, Long userId, Integer discount, Integer remainTime,
                               LocalDateTime startDate, LocalDateTime endDate) {
        this.couponId = couponId;
        this.userId = userId;
        this.discount = discount;
        this.remainTime = remainTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
