package com.letsintern.letsintern.domain.onlineprogram.dto.request;

public record OnlineProgramUpdateDTO(
        String title,
        String description,
        String link,
        boolean isVisible
) {
}
