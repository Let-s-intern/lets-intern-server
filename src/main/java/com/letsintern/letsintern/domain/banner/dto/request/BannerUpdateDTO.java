package com.letsintern.letsintern.domain.banner.dto.request;

import java.time.LocalDateTime;

public record BannerUpdateDTO(
        String title,
        String link,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Boolean isVisible,
        String imgUrl,
        String contents,
        String colorCode,
        String textColorCode
) {
}
