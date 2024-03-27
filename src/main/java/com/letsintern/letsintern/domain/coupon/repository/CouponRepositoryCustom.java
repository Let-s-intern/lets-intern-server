package com.letsintern.letsintern.domain.coupon.repository;

import com.letsintern.letsintern.domain.coupon.vo.CouponUserHistoryVo;

import java.util.Optional;

public interface CouponRepositoryCustom {
    Optional<CouponUserHistoryVo> findCouponUserHistoryByCodeAndUserId(String code, Long userId);
}
