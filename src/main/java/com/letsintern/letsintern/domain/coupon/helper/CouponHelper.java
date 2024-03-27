package com.letsintern.letsintern.domain.coupon.helper;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.coupon.exception.CouponExpiredException;
import com.letsintern.letsintern.domain.coupon.exception.CouponHistoryNotFound;
import com.letsintern.letsintern.domain.coupon.exception.CouponNotFound;
import com.letsintern.letsintern.domain.coupon.exception.CouponUsageLimitExceededException;
import com.letsintern.letsintern.domain.coupon.repository.CouponRepository;
import com.letsintern.letsintern.domain.coupon.vo.CouponUserHistoryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class CouponHelper {
    private final CouponRepository couponRepository;

    public void validateApplyTimeForCoupon(LocalDateTime endTime) {
        LocalDateTime now = LocalDateTime.now();
        if (endTime.isAfter(now))
            throw CouponExpiredException.EXCEPTION;
    }

    public void validateRemainTimeForUser(Integer remainTime) {
        if (remainTime <= 0)
            throw CouponUsageLimitExceededException.EXCEPTION;
    }

    public CouponUserHistoryVo findCouponUserHistoryVoOrThrow(Long userId, String code) {
        return couponRepository.findCouponUserHistoryByCodeAndUserId(code,userId)
                .orElseThrow(() -> CouponHistoryNotFound.EXCEPTION);
    }

    public Coupon findCouponOrThrow(Long couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() -> CouponNotFound.EXCEPTION);
    }

    public void saveCoupon(Coupon coupon) {
        couponRepository.save(coupon);
    }
}
