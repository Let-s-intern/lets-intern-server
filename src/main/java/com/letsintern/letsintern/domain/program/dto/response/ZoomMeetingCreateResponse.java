package com.letsintern.letsintern.domain.program.dto.response;

public record ZoomMeetingCreateResponse(
        String host_email,
        String id,
        String created_at,
        String join_url,
        String start_time,
        String password,
        String agenda,
        Integer duration,
        String timezone,
        String topic,
        Integer type
) {
}
