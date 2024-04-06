package com.letsintern.letsintern.domain.coupon.repository;

import com.letsintern.letsintern.domain.coupon.domain.CouponProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponProgramRepository extends JpaRepository<CouponProgram, Long> {
}
