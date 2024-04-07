package com.letsintern.letsintern.domain.banner.dto.response;

import com.letsintern.letsintern.domain.banner.vo.LineBannerAdminVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record LineBannerListResponse(List<LineBannerAdminVo> lineBannerList, PageInfo pageInfo) {
    public static LineBannerListResponse of(List<LineBannerAdminVo> lineBannerAdminVoList, PageInfo pageInfo) {
        return LineBannerListResponse.builder()
                .lineBannerList(lineBannerAdminVoList)
                .pageInfo(pageInfo)
                .build();
    }
}
