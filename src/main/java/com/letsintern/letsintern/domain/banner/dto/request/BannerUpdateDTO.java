package com.letsintern.letsintern.domain.banner.dto.request;

import com.letsintern.letsintern.domain.banner.domain.BannerStatus;

import java.time.LocalDateTime;

public record BannerUpdateDTO(
        String title,
        String link,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BannerStatus status,
        Boolean isVisible,
        String imgUrl,
        String contents,
        String colorCode,
        String textColorCode
) {
}
