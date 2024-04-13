package com.letsintern.letsintern.domain.banner.vo;

import lombok.Builder;

@Builder
public record BannerVo(
        Long bannerId,
        String title,
        String link,
        String imgUrl
) {
}
