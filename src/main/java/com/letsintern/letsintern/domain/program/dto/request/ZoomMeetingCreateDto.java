package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record ZoomMeetingCreateDto(
        String agenda,
        Boolean default_password,
        Integer duration,
        String start_time,
        String timezone,
        String topic,
        Integer type
) {
    public static ZoomMeetingCreateDto of(ProgramType type, String title, Integer th, LocalDateTime startDate) {
        String description = type.getValue() + " #" + th + " " + title;
        return ZoomMeetingCreateDto.builder()
                .agenda(description)
                .default_password(true)
                .duration(180)
                .start_time(startDate.toString() + ":00")
                .timezone("Asia/Seoul")
                .topic(description)
                .type(2)
                .build();
    }
}
