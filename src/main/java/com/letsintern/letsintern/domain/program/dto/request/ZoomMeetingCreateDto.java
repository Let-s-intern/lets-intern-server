package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.global.utils.EnumValueUtils;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.Objects;

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

    public static String getZoomMeetingTitle(ProgramRequestDto programRequestDto) {
        StringBuilder title = new StringBuilder();
        title.append(Objects.requireNonNull(EnumValueUtils.toEntityCode(ProgramType.class, programRequestDto.programType())).getDesc());
        title.append(" #").append(programRequestDto.th());
        title.append(" ").append(programRequestDto.title());
        return title.toString();
    }
    public static ZoomMeetingCreateDto of(ProgramRequestDto programRequestDto) {
        String title = getZoomMeetingTitle(programRequestDto);
        return ZoomMeetingCreateDto.builder()
                .agenda(title)
                .default_password(true)
                .duration(180)
                .start_time(programRequestDto.startDate().toString() + ":00")
                .timezone("Asia/Seoul")
                .topic(title)
                .type(2)
                .build();
    }
}
