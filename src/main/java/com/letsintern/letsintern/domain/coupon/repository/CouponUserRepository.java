package com.letsintern.letsintern.domain.coupon.repository;

import com.letsintern.letsintern.domain.coupon.domain.CouponUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponUserRepository extends JpaRepository<CouponUser, Long>, CouponUserRepositoryCustom {
    Optional<CouponUser> findByCouponIdAndUserId(Long couponId, Long userId);
}
