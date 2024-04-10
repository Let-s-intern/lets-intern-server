package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ProgramListResponseDto(
        List<?> programList,
        PageInfo pageInfo
) {
}
