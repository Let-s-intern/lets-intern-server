package com.letsintern.letsintern.domain.coupon.repository;

import com.letsintern.letsintern.domain.coupon.domain.CouponUser;
import com.letsintern.letsintern.domain.coupon.vo.CouponUserHistoryVo;

import java.util.Optional;

public interface CouponUserRepositoryCustom {
    Optional<CouponUserHistoryVo> findCouponUserHistoryByCodeAndUserId(String code, Long userId);
    Optional<CouponUser> findByCouponCodeAndUserId(String code, Long userId);
}
