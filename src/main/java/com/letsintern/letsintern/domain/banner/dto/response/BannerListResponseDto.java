package com.letsintern.letsintern.domain.banner.dto.response;

import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record BannerListResponseDto<T>(
        T bannerList,
        PageInfo pageInfo
) {
    public static <T> BannerListResponseDto<?> of(T bannerList, PageInfo pageInfo) {
        return BannerListResponseDto.builder()
                .bannerList(bannerList)
                .pageInfo(pageInfo)
                .build();
    }
}
