package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramType;

import java.time.LocalDateTime;
import java.util.List;

public record ChallengeBasicRequestDto(
        ProgramType type,
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
        List<Long> faqIdList,
        Integer topic,
        Integer finalHeadCount,
        String openKakaoLink,
        String openKakaoPassword
) {
}
