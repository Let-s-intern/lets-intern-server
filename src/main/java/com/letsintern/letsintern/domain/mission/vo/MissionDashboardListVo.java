package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionTopic;
import com.letsintern.letsintern.domain.mission.domain.MissionType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionDashboardListVo {

    private Integer missionTh;

    private String missionTopic;

    private MissionType missionType;

    private boolean isAttended;

    private AttendanceStatus attendanceStatus;

    @Builder
    public MissionDashboardListVo(Integer missionTh, MissionTopic missionTopic, MissionType missionType, Attendance attendance) {
        this.missionTh = missionTh;
        this.missionTopic = missionTopic.getValue();
        this.missionType = missionType;

        if(attendance != null) {
            this.isAttended = true;
            this.attendanceStatus = attendance.getStatus();
        } else {
            this.isAttended = false;
        }
    }
}
