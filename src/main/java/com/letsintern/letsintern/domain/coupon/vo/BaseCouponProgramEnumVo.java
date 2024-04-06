package com.letsintern.letsintern.domain.coupon.vo;

import com.letsintern.letsintern.domain.coupon.domain.CouponProgramType;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record BaseCouponProgramEnumVo(
        CouponProgramType couponProgramType
) {
    public static BaseCouponProgramEnumVo of(CouponProgramType couponProgramType) {
        return BaseCouponProgramEnumVo.builder()
                .couponProgramType(couponProgramType)
                .build();
    }
}
