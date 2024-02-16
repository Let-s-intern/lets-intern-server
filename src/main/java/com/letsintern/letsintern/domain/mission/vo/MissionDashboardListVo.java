package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionTopic;
import com.letsintern.letsintern.domain.mission.domain.MissionType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionDashboardListVo {

    private Integer missionTh;

    private String missionTopic;

    private MissionType missionType;

    private LocalDateTime missionStartDate;

    private boolean isAttended;

    private AttendanceStatus attendanceStatus;

    private String attendanceComments;

    @Builder
    public MissionDashboardListVo(Integer missionTh, MissionTopic missionTopic, MissionType missionType, LocalDateTime missionStartDate, Attendance attendance) {
        this.missionTh = missionTh;
        this.missionTopic = missionTopic.getValue();
        this.missionType = missionType;
        this.missionStartDate = missionStartDate;

        if(attendance != null) {
            this.isAttended = true;
            this.attendanceStatus = attendance.getStatus();
            this.attendanceComments = attendance.getComments();
        } else {
            this.isAttended = false;
        }
    }
}
