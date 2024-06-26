package com.letsintern.letsintern.domain.coupon.vo;

import com.letsintern.letsintern.domain.coupon.domain.CouponType;
import com.letsintern.letsintern.domain.coupon.dto.request.BaseCouponRequestDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BaseCouponEnumVo(
        CouponType couponType,
        String name,
        String code,
        Integer discount,
        Integer time,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
    public static BaseCouponEnumVo of(BaseCouponRequestDto baseCouponRequestDto,
                                      CouponType couponType) {
        return BaseCouponEnumVo.builder()
                .couponType(couponType)
                .name(baseCouponRequestDto.name())
                .code(baseCouponRequestDto.code())
                .discount(baseCouponRequestDto.discount())
                .time(baseCouponRequestDto.time())
                .startDate(baseCouponRequestDto.startDate())
                .endDate(baseCouponRequestDto.endDate())
                .build();
    }
}
