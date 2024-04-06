package com.letsintern.letsintern.domain.coupon.repository;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long>, CouponCustomRepository {
    Optional<Coupon> findByCode(String code);
}
