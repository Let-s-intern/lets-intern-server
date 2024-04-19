package com.letsintern.letsintern.domain.banner.dto.response;

import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record BannerAdminListResponse(List<?> bannerList, PageInfo pageInfo) {
    public static BannerAdminListResponse of(List<?> bannerAdminVoList, PageInfo pageInfo) {
        return BannerAdminListResponse.builder()
                .bannerList(bannerAdminVoList)
                .pageInfo(pageInfo)
                .build();
    }
}
