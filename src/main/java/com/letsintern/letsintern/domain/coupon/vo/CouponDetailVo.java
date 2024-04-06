package com.letsintern.letsintern.domain.coupon.vo;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.coupon.domain.CouponProgram;
import lombok.Builder;

import java.util.List;

@Builder
public record CouponDetailVo(
        Coupon coupon,
        List<CouponProgram> couponProgramList
) {
}
