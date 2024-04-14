package com.letsintern.letsintern.domain.banner.vo;

import lombok.Builder;

@Builder
public record LineBannerVo(
        Long bannerId,
        String title,
        String link,
        String contents,
        String colorCode,
        String textColorCode
) {
}
