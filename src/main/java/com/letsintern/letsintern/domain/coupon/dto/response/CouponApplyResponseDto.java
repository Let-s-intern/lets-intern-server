package com.letsintern.letsintern.domain.coupon.dto.response;

import lombok.Builder;

@Builder
public record CouponApplyResponseDto(
        Integer discount
) {
    public static CouponApplyResponseDto of(Integer discount) {
        return CouponApplyResponseDto.builder()
                .discount(discount)
                .build();
    }
}
