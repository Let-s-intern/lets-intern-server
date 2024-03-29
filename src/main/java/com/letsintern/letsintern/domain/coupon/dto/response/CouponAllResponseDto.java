package com.letsintern.letsintern.domain.coupon.dto.response;

import com.letsintern.letsintern.domain.coupon.vo.CouponAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record CouponAllResponseDto(
        List<CouponAdminVo> couponList,
        PageInfo pageInfo
) {
    public static CouponAllResponseDto of(Page<CouponAdminVo> couponList, PageInfo pageInfo) {
        return CouponAllResponseDto.builder()
                .couponList(couponList.getContent())
                .pageInfo(pageInfo)
                .build();
    }
}
