package com.letsintern.letsintern.domain.coupon.vo;

import com.letsintern.letsintern.domain.coupon.domain.CouponProgramType;
import com.letsintern.letsintern.domain.coupon.domain.CouponType;
import com.letsintern.letsintern.domain.coupon.dto.request.BaseCouponRequestDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BaseCouponEnumVo(
        CouponType couponType,
        CouponProgramType couponProgramType,
        String name,
        String code,
        Integer discount,
        Integer time,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
    public static BaseCouponEnumVo of(BaseCouponRequestDto baseCouponRequestDto,
                                      CouponType couponType,
                                      CouponProgramType couponProgramType) {
        return BaseCouponEnumVo.builder()
                .couponType(couponType)
                .couponProgramType(couponProgramType)
                .name(baseCouponRequestDto.name())
                .code(baseCouponRequestDto.code())
                .discount(baseCouponRequestDto.discount())
                .time(baseCouponRequestDto.time())
                .startDate(baseCouponRequestDto.startDate())
                .endDate(baseCouponRequestDto.endDate())
                .build();
    }
}
