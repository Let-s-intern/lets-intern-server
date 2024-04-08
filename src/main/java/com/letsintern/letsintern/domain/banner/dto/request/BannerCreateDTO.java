package com.letsintern.letsintern.domain.banner.dto.request;

import com.letsintern.letsintern.domain.banner.domain.BannerType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BannerCreateDTO(
        @NotNull BannerType type,
        @NotNull String title,
        @NotNull String link,
        @NotNull LocalDateTime startDate,
        @NotNull LocalDateTime endDate,
        @Nullable String imgUrl,
        @Nullable String contents,
        @Nullable String colorCode,
        @Nullable String textColorCode
) {
}
