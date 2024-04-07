package com.letsintern.letsintern.domain.banner.domain.linebanner.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LineBannerAdminVo(
        Long id,
        String title,
        String link,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean isVisible,
        String contents,
        String colorCode
) {
}
