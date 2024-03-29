package com.letsintern.letsintern.domain.coupon.mapper;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.coupon.domain.CouponProgramType;
import com.letsintern.letsintern.domain.coupon.domain.CouponType;
import com.letsintern.letsintern.domain.coupon.dto.request.BaseCouponRequestDto;
import com.letsintern.letsintern.domain.coupon.dto.response.CouponAllResponseDto;
import com.letsintern.letsintern.domain.coupon.vo.BaseCouponEnumVo;
import com.letsintern.letsintern.domain.coupon.vo.CouponAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import static com.letsintern.letsintern.global.utils.EnumValueUtils.toEntityCode;

@RequiredArgsConstructor
@Component
public class CouponMapper {
    public Coupon toEntity(BaseCouponEnumVo baseCouponEnumVo) {
        return Coupon.createCoupon(baseCouponEnumVo);
    }

    public BaseCouponEnumVo toCouponEnumVo(BaseCouponRequestDto baseCouponRequestDto) {
        CouponType couponType = toEntityCode(CouponType.class, baseCouponRequestDto.couponType());
        CouponProgramType couponProgramType = toEntityCode(CouponProgramType.class, baseCouponRequestDto.programType());
        return BaseCouponEnumVo.of(baseCouponRequestDto, couponType, couponProgramType);
    }

    public CouponAllResponseDto toCouponAllResponseDto(Page<CouponAdminVo> couponList) {
        PageInfo pageInfo = PageInfo.of(couponList);
        return CouponAllResponseDto.of(couponList, pageInfo);
    }
}
