package com.letsintern.letsintern.domain.program.vo.program;

import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProgramThumbnailVo(
        Long id,
        ProgramStatus status,
        ProgramType type,
        Integer th,
        String title,
        LocalDateTime dueDate,
        LocalDateTime startDate
) {
}
