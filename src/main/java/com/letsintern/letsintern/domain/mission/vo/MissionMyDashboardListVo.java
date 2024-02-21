package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionMyDashboardListVo {

    private Long id;

    private Integer th;

    private String title;

    private MissionStatus missionStatus;

    private MissionType missionType;

    private AttendanceStatus attendanceStatus;

    private AttendanceResult attendanceResult;

    private Boolean attendanceIsRefunded;

    @Builder
    public MissionMyDashboardListVo(Long id, Integer th, String title, MissionStatus missionStatus, MissionType missionType, Attendance attendance) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.missionStatus = missionStatus;
        this.missionType = missionType;

        if(attendance != null) {
            this.attendanceStatus = attendance.getStatus();
            this.attendanceResult = attendance.getResult();
            this.attendanceIsRefunded = attendance.getIsRefunded();
        } else {
            this.attendanceStatus = AttendanceStatus.ABSENT;
        }

    }
}
