package com.letsintern.letsintern.domain.attendance.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AttendanceDashboardVo {

    private Long attendanceId;

    private String attendanceLink;

    private Integer missionTh;

    private String missionTitle;

    @Builder
    public AttendanceDashboardVo(Long attendanceId, String attendanceLink, Integer missionTh, String missionTitle) {
        this.attendanceId = attendanceId;
        this.attendanceLink = attendanceLink;
        this.missionTh = missionTh;
        this.missionTitle = missionTitle;
    }
}
