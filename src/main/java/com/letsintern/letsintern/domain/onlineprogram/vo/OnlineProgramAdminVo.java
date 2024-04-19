package com.letsintern.letsintern.domain.onlineprogram.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OnlineProgramAdminVo(
        Long id,
        String title,
        String description,
        String link,
        String thumbnailUrl,
        boolean isVisible,
        LocalDateTime createdAt
) {
}
