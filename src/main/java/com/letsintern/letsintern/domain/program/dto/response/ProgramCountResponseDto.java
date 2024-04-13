package com.letsintern.letsintern.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record ProgramCountResponseDto(
        Long count
) {
    public static ProgramCountResponseDto of(Long count) {
        return ProgramCountResponseDto.builder()
                .count(count)
                .build();
    }
}
