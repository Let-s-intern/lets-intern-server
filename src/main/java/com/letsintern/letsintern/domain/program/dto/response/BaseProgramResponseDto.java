package com.letsintern.letsintern.domain.program.dto.response;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record BaseProgramResponseDto<T>(
        T programInfo
) {
    public static <T> BaseProgramResponseDto<?> of(T programInfo) {
        return BaseProgramResponseDto.builder()
                .programInfo(programInfo)
                .build();
    }
}
