package com.letsintern.letsintern.domain.coupon.vo;

import com.letsintern.letsintern.domain.coupon.domain.CouponProgramType;
import com.letsintern.letsintern.domain.coupon.domain.CouponType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CouponAdminVo(
        Long couponId,
        CouponType couponType,
        CouponProgramType couponProgramType,
        String name,
        String code,
        Integer discount,
        Integer time,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime createDate
) {
}
