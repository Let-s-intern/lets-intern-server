package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramWay;

import java.time.LocalDateTime;
import java.util.List;

public record ProgramRequestDto(
        Integer th,
        String title,
        Integer headcount,
        LocalDateTime dueDate,
        LocalDateTime announcementDate,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String contents,
        Integer way,
        String location,
        String notice,
        List<Long> faqIdList
) {
}
