package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionAdminApplicationVo {

    private Long missionId;

    private Integer missionTh;

    private AttendanceStatus attendanceStatus;

    private AttendanceResult attendanceResult;

    private Boolean attendanceIsRefunded;

    private boolean limitedContents;

    @Builder
    public MissionAdminApplicationVo(Long missionId, Integer missionTh, Attendance attendance) {
        this.missionId = missionId;
        this.missionTh = missionTh;
        if(attendance != null) {
            this.attendanceStatus = attendance.getStatus();
            this.attendanceResult = attendance.getResult();
            this.attendanceIsRefunded = attendance.getIsRefunded();
            if(attendance.getMission().getLimitedContentsId() != null && !(attendance.getMission().getStatus().equals(MissionStatus.WAITING))
                    && (attendance.getStatus().equals(AttendanceStatus.PRESENT) || attendance.getStatus().equals(AttendanceStatus.LATE))
                    && (attendance.getResult().equals(AttendanceResult.PASS))) {
                this.limitedContents = true;
            } else {
                this.limitedContents = false;
            }
        } else {
            this.attendanceStatus = AttendanceStatus.ABSENT;
            this.limitedContents = false;
        }
    }
}
