package com.letsintern.letsintern.domain.onlineprogram.vo;

import lombok.Builder;

@Builder
public record OnlineProgramAdminVo(
        Long id,
        String title,
        String description,
        String link,
        String thumbnailUrl
) {
}
