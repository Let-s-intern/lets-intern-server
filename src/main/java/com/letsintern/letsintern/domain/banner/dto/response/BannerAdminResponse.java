package com.letsintern.letsintern.domain.banner.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record BannerAdminResponse<T>(
        T bannerInfo
) {
    public static <T> BannerAdminResponse<?> from(T banner) {
        return BannerAdminResponse.builder()
                .bannerInfo(banner)
                .build();
    }
}
