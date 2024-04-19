package com.letsintern.letsintern.domain.coupon.dto.response;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.coupon.domain.CouponProgramType;
import com.letsintern.letsintern.domain.coupon.domain.CouponType;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record CouponResponseDto(
        CouponType couponType,
        List<CouponProgramType> couponProgramList,
        String name,
        String code,
        Integer discount,
        Integer time,
        LocalDateTime startDate,
        LocalDateTime endDate,
        LocalDateTime createDate
) {
    public static CouponResponseDto of(Coupon coupon, List<CouponProgramType> couponProgramList) {
        return CouponResponseDto.builder()
                .couponType(coupon.getCouponType())
                .couponProgramList(couponProgramList)
                .name(coupon.getName())
                .code(coupon.getCode())
                .discount(coupon.getDiscount())
                .time(coupon.getTime())
                .startDate(coupon.getStartDate())
                .endDate(coupon.getEndDate())
                .createDate(coupon.getCreateDate())
                .build();
    }
}
