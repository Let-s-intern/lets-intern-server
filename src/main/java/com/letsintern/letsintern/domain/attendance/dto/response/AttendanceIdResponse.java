package com.letsintern.letsintern.domain.attendance.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AttendanceIdResponse {

    private Long attendanceId;

    @Builder
    private AttendanceIdResponse(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public static AttendanceIdResponse from(Long attendanceId) {
        return AttendanceIdResponse.builder()
                .attendanceId(attendanceId)
                .build();
    }
}
