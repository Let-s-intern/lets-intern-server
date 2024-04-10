package com.letsintern.letsintern.domain.program.vo.letschat;

import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LetsChatMentorInfoVo(
        String title,
        ProgramWay way,
        String location,
        String link,
        String linkPassword,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer applicationCount
) {
}
