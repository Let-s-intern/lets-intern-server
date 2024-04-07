package com.letsintern.letsintern.domain.onlineprogram.dto.request;

import jakarta.validation.constraints.NotNull;

public record OnlineProgramCreateDTO(
        @NotNull String title,
        @NotNull String description,
        @NotNull String link,
        @NotNull String thumbnailImgUrl
) {
}
