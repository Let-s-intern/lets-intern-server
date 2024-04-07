package com.letsintern.letsintern.domain.coupon.repository;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.coupon.vo.CouponAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CouponCustomRepository {
    Page<CouponAdminVo> findCouponAdminInfo(Pageable pageable);
    Optional<Coupon> existCouponCodeExceptedCouponId(Long couponId, String code);
}
