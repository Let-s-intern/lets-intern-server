package com.letsintern.letsintern.domain.attendance.dto.response;

import com.letsintern.letsintern.domain.attendance.vo.AttendanceAdminVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AttendanceAdminListResponse {

    private List<AttendanceAdminVo> attendanceList;

    @Builder
    private AttendanceAdminListResponse(List<AttendanceAdminVo> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public static AttendanceAdminListResponse from(List<AttendanceAdminVo> attendanceList) {
        return AttendanceAdminListResponse.builder()
                .attendanceList(attendanceList)
                .build();
    }
}
