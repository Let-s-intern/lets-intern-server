package com.letsintern.letsintern.domain.banner.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BannerCreateDTO(
        @NotNull String title,
        @NotNull String link,
        @NotNull LocalDateTime startDate,
        @NotNull LocalDateTime endDate,
        @Nullable String imgUrl,
        @Nullable String contents,
        @Nullable String colorCode
) {
}
