package com.letsintern.letsintern.domain.coupon.dto.response;

import com.letsintern.letsintern.domain.coupon.domain.CouponProgramType;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CouponProgramResponseDto(
        CouponProgramType couponProgramType
) {
    public static CouponProgramResponseDto of(CouponProgramType couponProgramType) {
        return CouponProgramResponseDto.builder()
                .couponProgramType(couponProgramType)
                .build();
    }
}
