package com.letsintern.letsintern.domain.coupon.service;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.coupon.domain.CouponProgram;
import com.letsintern.letsintern.domain.coupon.domain.CouponProgramType;
import com.letsintern.letsintern.domain.coupon.dto.request.BaseCouponProgramRequestDto;
import com.letsintern.letsintern.domain.coupon.dto.request.BaseCouponRequestDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponAllResponseDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponApplyResponseDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponProgramResponseDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponResponseDto;
import com.letsintern.letsintern.domain.coupon.helper.CouponHelper;
import com.letsintern.letsintern.domain.coupon.mapper.CouponMapper;
import com.letsintern.letsintern.domain.coupon.vo.*;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Transactional
@Service
public class CouponService {
    private final CouponHelper couponHelper;
    private final CouponMapper couponMapper;

    public CouponResponseDto getCoupon(Long couponId) {
        Coupon coupon = couponHelper.findCouponOrThrow(couponId);
        List<CouponProgramResponseDto> couponProgramResponseDtoList
                = couponMapper.toCouponProgramResponseDtoList(coupon.getCouponProgramList());
        return couponMapper.toCouponResponseDto(coupon, couponProgramResponseDtoList);
    }

    public CouponAllResponseDto getCoupons(Pageable pageable) {
        Page<CouponAdminVo> couponList = couponHelper.findCouponAdminInfo(pageable);
        return couponMapper.toCouponAllResponseDto(couponList);
    }

    public CouponApplyResponseDto getAvailableCoupon(PrincipalDetails principalDetails,
                                                     String code,
                                                     Integer type) {
        User user = principalDetails.getUser();
        CouponUserHistoryVo couponUserHistoryVo = couponHelper.findCouponUserHistoryVoOrCreate(user, code);
        CouponProgramType couponProgramType = couponMapper.toCouponProgramType(type);
        couponHelper.validateApplyTimeForCoupon(couponUserHistoryVo.coupon().getStartDate(), couponUserHistoryVo.coupon().getEndDate());
        couponHelper.validateRemainTimeForUser(couponUserHistoryVo.coupon().getTime());
        couponHelper.validateAvailableCouponProgram(couponUserHistoryVo.coupon().getId(), couponProgramType);
        return CouponApplyResponseDto.of(couponUserHistoryVo.coupon().getDiscount());
    }

    public void createNewCoupon(BaseCouponRequestDto baseCouponRequestDto) {
        couponHelper.validateDuplicateCouponCode(null, baseCouponRequestDto.code());
        BaseCouponEnumVo baseCouponEnumVo = couponMapper.toCouponEnumVo(baseCouponRequestDto);
        List<BaseCouponProgramEnumVo> baseCouponProgramEnumVoList = couponMapper.toCouponProgramEnumVoList(baseCouponRequestDto.programTypeList());
        Coupon newCoupon = createCouponAndSave(baseCouponEnumVo);
        createCouponProgramsAndSave(baseCouponProgramEnumVoList, newCoupon);
    }

    public void updateCouponInfo(Long couponId, BaseCouponRequestDto baseCouponRequestDto) {
        couponHelper.validateDuplicateCouponCode(couponId, baseCouponRequestDto.code());
        Coupon coupon = couponHelper.findCouponOrThrow(couponId);
        BaseCouponEnumVo baseCouponEnumVo = couponMapper.toCouponEnumVo(baseCouponRequestDto);
        coupon.updateCoupon(baseCouponEnumVo);
        checkUpdatedCouponProgramAndUpdate(baseCouponRequestDto.programTypeList(), coupon);
    }

    private void checkUpdatedCouponProgramAndUpdate(List<BaseCouponProgramRequestDto> baseCouponProgramRequestDtoList,
                                                    Coupon coupon) {
        if (Objects.isNull(baseCouponProgramRequestDtoList)) return;
        deleteCouponPrograms(coupon);
        List<BaseCouponProgramEnumVo> baseCouponProgramEnumVoList = couponMapper.toCouponProgramEnumVoList(baseCouponProgramRequestDtoList);
        createCouponProgramsAndSave(baseCouponProgramEnumVoList, coupon);
    }

    public void deleteCoupon(Long couponId) {
        Coupon coupon = couponHelper.findCouponOrThrow(couponId);
        couponHelper.deleteCoupon(coupon);
    }

    private Coupon createCouponAndSave(BaseCouponEnumVo baseCouponEnumVo) {
        Coupon newCoupon = couponMapper.toEntityCoupon(baseCouponEnumVo);
        return couponHelper.saveCoupon(newCoupon);
    }

    private void createCouponProgramsAndSave(List<BaseCouponProgramEnumVo> baseCouponProgramEnumVoList,
                                             Coupon coupon) {
        List<CouponProgram> couponProgramList = couponMapper.toEntityListCouponProgram(baseCouponProgramEnumVoList, coupon);
        couponHelper.saveCouponProgramList(couponProgramList);
    }

    private void deleteCouponPrograms(Coupon coupon) {
        couponHelper.deleteCouponProgramList(coupon.getCouponProgramList());
        coupon.resetCouponProgramType();
    }
}
