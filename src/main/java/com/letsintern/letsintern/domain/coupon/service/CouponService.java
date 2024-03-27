package com.letsintern.letsintern.domain.coupon.service;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.coupon.dto.request.BaseCouponRequestDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponApplyResponseDto;
import com.letsintern.letsintern.domain.coupon.helper.CouponHelper;
import com.letsintern.letsintern.domain.coupon.mapper.CouponMapper;
import com.letsintern.letsintern.domain.coupon.vo.BaseCouponEnumVo;
import com.letsintern.letsintern.domain.coupon.vo.CouponUserHistoryVo;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CouponService {
    private final CouponHelper couponHelper;
    private final CouponMapper couponMapper;

    public CouponApplyResponseDto getAvailableCoupon(PrincipalDetails principalDetails,
                                                     String code) {
        User user = principalDetails.getUser();
        CouponUserHistoryVo couponUserHistoryVo = couponHelper.findCouponUserHistoryVoOrThrow(user.getId(), code);
        validateApplyCoupon(couponUserHistoryVo);
        return CouponApplyResponseDto.of(couponUserHistoryVo.discount());
    }

    public void createNewCoupon(BaseCouponRequestDto baseCouponRequestDto) {
        BaseCouponEnumVo baseCouponEnumVo = couponMapper.toCouponEnumVo(baseCouponRequestDto);
        createCouponAndSave(baseCouponEnumVo);
    }

    public void updateCouponInfo(Long couponId, BaseCouponRequestDto baseCouponRequestDto) {
        Coupon coupon = couponHelper.findCouponOrThrow(couponId);
        BaseCouponEnumVo baseCouponEnumVo = couponMapper.toCouponEnumVo(baseCouponRequestDto);
        coupon.updateCoupon(baseCouponEnumVo);
    }

    private void createCouponAndSave(BaseCouponEnumVo baseCouponEnumVo) {
        Coupon newCoupon = couponMapper.toEntity(baseCouponEnumVo);
        couponHelper.saveCoupon(newCoupon);
    }

    private void validateApplyCoupon(CouponUserHistoryVo couponUserHistoryVo) {
        couponHelper.validateApplyTimeForCoupon(couponUserHistoryVo.endDate());
        couponHelper.validateRemainTimeForUser(couponUserHistoryVo.remainTime());
    }

}
