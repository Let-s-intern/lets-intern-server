package com.letsintern.letsintern.domain.coupon.repository;

import com.letsintern.letsintern.domain.coupon.vo.CouponAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponCustomRepository {
    Page<CouponAdminVo> findCouponAdminInfo(Pageable pageable);
}
