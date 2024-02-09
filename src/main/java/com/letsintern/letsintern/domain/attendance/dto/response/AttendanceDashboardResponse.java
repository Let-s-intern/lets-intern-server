package com.letsintern.letsintern.domain.attendance.dto.response;

import com.letsintern.letsintern.domain.attendance.vo.AttendanceDashboardVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AttendanceDashboardResponse {

    private String name;

    private String wishJob;

    private String introduction;

    private List<AttendanceDashboardVo> attendanceList;

    @Builder
    private AttendanceDashboardResponse(String name, String wishJob, String introduction, List<AttendanceDashboardVo> attendanceList) {
        this.name = name;
        this.wishJob = wishJob;
        this.introduction = introduction;
        this.attendanceList = attendanceList;
    }

    public static AttendanceDashboardResponse of(String name, String wishJob, String introduction, List<AttendanceDashboardVo> attendanceList) {
        return AttendanceDashboardResponse.builder()
                .name(name)
                .wishJob(wishJob)
                .introduction(introduction)
                .attendanceList(attendanceList)
                .build();
    }
}
