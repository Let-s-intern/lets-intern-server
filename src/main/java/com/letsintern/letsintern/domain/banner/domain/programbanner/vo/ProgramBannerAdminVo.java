package com.letsintern.letsintern.domain.banner.domain.programbanner.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProgramBannerAdminVo(
        Long id,
        String title,
        String link,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean isVisible,
        String imgUrl
) {
}
