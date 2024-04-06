package com.letsintern.letsintern.domain.coupon.helper;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.coupon.domain.CouponProgram;
import com.letsintern.letsintern.domain.coupon.domain.CouponUser;
import com.letsintern.letsintern.domain.coupon.exception.*;
import com.letsintern.letsintern.domain.coupon.repository.CouponProgramRepository;
import com.letsintern.letsintern.domain.coupon.repository.CouponRepository;
import com.letsintern.letsintern.domain.coupon.repository.CouponUserRepository;
import com.letsintern.letsintern.domain.coupon.vo.CouponAdminVo;
import com.letsintern.letsintern.domain.coupon.vo.CouponUserHistoryVo;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CouponHelper {
    private final CouponRepository couponRepository;
    private final CouponUserRepository couponUserRepository;
    private final CouponProgramRepository couponProgramRepository;

    public void validateApplyTimeForCoupon(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(startTime))
            throw CouponBeforeTimeException.EXCEPTION;
        if (now.isAfter(endTime))
            throw CouponExpiredException.EXCEPTION;
    }

    public void validateRemainTimeForUser(Integer remainTime) {
        if (remainTime == -1)
            return;
        if (remainTime <= 0)
            throw CouponUsageLimitExceededException.EXCEPTION;
    }

    public Page<CouponAdminVo> findCouponAdminInfo(Pageable pageable) {
        return couponRepository.findCouponAdminInfo(pageable);
    }

    public CouponUserHistoryVo findCouponUserHistoryVoOrCreate(User user, String code) {
        return couponUserRepository.findCouponUserHistoryByCodeAndUserId(code, user.getId())
                .orElseGet(() -> {
                    Coupon coupon = findCouponByCodeOrThrow(code);
                    return CouponUserHistoryVo.of(coupon, coupon.getTime());
                });
    }

    public CouponUser findCouponUserByCouponIdAndUserIdThrow(Long couponId, Long userId) {
        return couponUserRepository.findByCouponIdAndUserId(couponId, userId)
                .orElseThrow(() -> CouponHistoryNotFound.EXCEPTION);
    }

    public Coupon findCouponByCodeOrThrow(String code) {
        return couponRepository.findByCode(code)
                .orElseThrow(() -> CouponNotFound.EXCEPTION);
    }

    public Coupon findCouponOrThrow(Long couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() -> CouponNotFound.EXCEPTION);
    }

    public void saveCoupon(Coupon coupon) {
        couponRepository.save(coupon);
    }

    public CouponUser saveCouponUser(CouponUser couponUser) {
        return couponUserRepository.save(couponUser);
    }

    public CouponProgram saveCouponProgram(CouponProgram couponProgram) {
        return couponProgramRepository.save(couponProgram);
    }

    public void saveCouponProgramList(List<CouponProgram> couponProgramList) {
        couponProgramRepository.saveAll(couponProgramList);
    }

    public void deleteCoupon(Coupon coupon) {
        couponRepository.delete(coupon);
    }

    public void deleteCouponProgramList(List<CouponProgram> couponProgramList) {
        couponProgramRepository.deleteAll(couponProgramList);
    }
}
