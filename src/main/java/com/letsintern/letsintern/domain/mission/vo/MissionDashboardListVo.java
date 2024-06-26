package com.letsintern.letsintern.domain.mission.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionTopic;
import com.letsintern.letsintern.domain.mission.domain.MissionType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionDashboardListVo {

    private Long missionId;

    private Integer missionTh;

    private String missionTopic;

    private MissionType missionType;

    private LocalDateTime missionStartDate;

    private LocalDateTime missionEndDate;

    private String missionComments;

    @JsonIgnore
    private Integer missionRefund;

    private AttendanceStatus attendanceStatus;

    private AttendanceResult attendanceResult;

    private Boolean attendanceIsRefunded;

    @Builder
    public MissionDashboardListVo(Long missionId, Integer missionTh, MissionTopic missionTopic, MissionType missionType,
                                  LocalDateTime missionStartDate, LocalDateTime missionEndDate, String missionComments, Integer missionRefund, Attendance attendance) {
        this.missionId = missionId;
        this.missionTh = missionTh;
        this.missionTopic = missionTopic.getValue();
        this.missionType = missionType;
        this.missionStartDate = missionStartDate;
        this.missionEndDate = missionEndDate;
        this.missionRefund = missionRefund;

        if(attendance != null) {
            this.attendanceStatus = attendance.getStatus();
            this.attendanceResult = attendance.getResult();
            this.attendanceIsRefunded = attendance.getIsRefunded();
            if(attendance.getStatus().equals(AttendanceStatus.PRESENT) && attendance.getResult().equals(AttendanceResult.PASS)) {
                this.missionComments = missionComments;
            }
        } else {
            this.attendanceStatus = AttendanceStatus.ABSENT;
        }
    }
}
