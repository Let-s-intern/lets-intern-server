package com.letsintern.letsintern.domain.coupon.service;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.coupon.dto.request.BaseCouponRequestDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponAllResponseDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponApplyResponseDto;
import com.letsintern.letsintern.domain.coupon.helper.CouponHelper;
import com.letsintern.letsintern.domain.coupon.mapper.CouponMapper;
import com.letsintern.letsintern.domain.coupon.vo.BaseCouponEnumVo;
import com.letsintern.letsintern.domain.coupon.vo.CouponAdminVo;
import com.letsintern.letsintern.domain.coupon.vo.CouponUserHistoryVo;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CouponService {
    private final CouponHelper couponHelper;
    private final CouponMapper couponMapper;

    public CouponAllResponseDto getCoupons(Pageable pageable) {
        Page<CouponAdminVo> couponList = couponHelper.findCouponAdminInfo(pageable);
        return couponMapper.toCouponAllResponseDto(couponList);
    }

    public CouponApplyResponseDto getAvailableCoupon(PrincipalDetails principalDetails,
                                                     String code) {
        User user = principalDetails.getUser();
        CouponUserHistoryVo couponUserHistoryVo = couponHelper.findCouponUserHistoryVoOrCreate(user, code);
        couponHelper.validateApplyTimeForCoupon(couponUserHistoryVo.coupon().getStartDate(), couponUserHistoryVo.coupon().getEndDate());
        couponHelper.validateRemainTimeForUser(couponUserHistoryVo.coupon().getTime());
        return CouponApplyResponseDto.of(couponUserHistoryVo.coupon().getDiscount());
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

    public void deleteCoupon(Long couponId) {
        Coupon coupon = couponHelper.findCouponOrThrow(couponId);
        couponHelper.deleteCoupon(coupon);
    }

    private void createCouponAndSave(BaseCouponEnumVo baseCouponEnumVo) {
        Coupon newCoupon = couponMapper.toEntity(baseCouponEnumVo);
        couponHelper.saveCoupon(newCoupon);
    }
}
