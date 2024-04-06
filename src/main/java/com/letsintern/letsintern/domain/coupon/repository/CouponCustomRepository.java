package com.letsintern.letsintern.domain.coupon.repository;

import com.letsintern.letsintern.domain.coupon.vo.CouponAdminVo;
import com.letsintern.letsintern.domain.coupon.vo.CouponDetailVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CouponCustomRepository {
    Page<CouponAdminVo> findCouponAdminInfo(Pageable pageable);
    Optional<CouponDetailVo> findCouponDetailInfo(Long couponId);
}
