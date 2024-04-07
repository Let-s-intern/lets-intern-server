package com.letsintern.letsintern.domain.banner.domain.mainbanner.vo;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record MainBannerAdminVo(
        Long id,
        String title,
        String link,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean isVisible,
        String imgUrl
) {
}
