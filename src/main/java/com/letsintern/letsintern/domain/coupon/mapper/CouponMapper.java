package com.letsintern.letsintern.domain.coupon.mapper;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.coupon.domain.CouponProgram;
import com.letsintern.letsintern.domain.coupon.domain.CouponProgramType;
import com.letsintern.letsintern.domain.coupon.domain.CouponType;
import com.letsintern.letsintern.domain.coupon.dto.request.BaseCouponProgramRequestDto;
import com.letsintern.letsintern.domain.coupon.dto.request.BaseCouponRequestDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponAllResponseDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponProgramResponseDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponResponseDto;
import com.letsintern.letsintern.domain.coupon.vo.BaseCouponEnumVo;
import com.letsintern.letsintern.domain.coupon.vo.BaseCouponProgramEnumVo;
import com.letsintern.letsintern.domain.coupon.vo.CouponAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.letsintern.letsintern.global.utils.EnumValueUtils.toEntityCode;

@RequiredArgsConstructor
@Component
public class CouponMapper {
    public Coupon toEntityCoupon(BaseCouponEnumVo baseCouponEnumVo) {
        return Coupon.createCoupon(baseCouponEnumVo);
    }

    public CouponProgram toEntityCouponProgram(BaseCouponProgramEnumVo baseCouponProgramEnumVo, Coupon coupon) {
        return CouponProgram.createCouponProgram(baseCouponProgramEnumVo, coupon);
    }

    public List<CouponProgram> toEntityListCouponProgram(List<BaseCouponProgramEnumVo> baseCouponProgramEnumVoList, Coupon coupon) {
        return baseCouponProgramEnumVoList.stream()
                .map(baseCouponProgramEnumVo -> CouponProgram.createCouponProgram(baseCouponProgramEnumVo, coupon))
                .collect(Collectors.toList());
    }

    public BaseCouponEnumVo toCouponEnumVo(BaseCouponRequestDto baseCouponRequestDto) {
        CouponType couponType = toEntityCode(CouponType.class, baseCouponRequestDto.couponType());
        return BaseCouponEnumVo.of(baseCouponRequestDto, couponType);
    }

    public List<BaseCouponProgramEnumVo> toCouponProgramEnumVoList(List<BaseCouponProgramRequestDto> baseCouponProgramRequestDtoList) {
        return baseCouponProgramRequestDtoList.stream()
                .map(this::toCouponProgramEnumVo)
                .collect(Collectors.toList());
    }

    public BaseCouponProgramEnumVo toCouponProgramEnumVo(BaseCouponProgramRequestDto baseCouponProgramRequestDto) {
        CouponProgramType couponProgramType = toCouponProgramType(baseCouponProgramRequestDto.programType());
        return BaseCouponProgramEnumVo.of(couponProgramType);
    }

    public CouponAllResponseDto toCouponAllResponseDto(Page<CouponAdminVo> couponList) {
        PageInfo pageInfo = PageInfo.of(couponList);
        return CouponAllResponseDto.of(couponList, pageInfo);
    }

    public CouponResponseDto toCouponResponseDto(Coupon coupon, List<CouponProgramType> couponProgramResponseDtoList) {
        return CouponResponseDto.of(coupon, couponProgramResponseDtoList);
    }

    public List<CouponProgramType> toCouponProgramTypeList(List<CouponProgram> couponProgramList) {
        return couponProgramList.stream()
                .map(CouponProgram::getCouponProgramType)
                .collect(Collectors.toList());
    }

    public CouponProgramType toCouponProgramType(Integer type) {
        return toEntityCode(CouponProgramType.class, type);
    }
}
