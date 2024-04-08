package com.letsintern.letsintern.domain.banner.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record BannerIdResponse(Long bannerId) {

    public static BannerIdResponse from(Long bannerId) {
        return BannerIdResponse.builder()
                .bannerId(bannerId)
                .build();
    }
}
