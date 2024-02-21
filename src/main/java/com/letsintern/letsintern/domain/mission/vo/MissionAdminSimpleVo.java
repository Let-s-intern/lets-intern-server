package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.mission.domain.MissionStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionAdminSimpleVo {

    private Long missionId;

    private Integer missionTh;

    private LocalDateTime missionStartDate;

    private Integer attendanceCount;

    private Integer lateAttendanceCount;

    private MissionStatus missionStatus;

    @Builder
    public MissionAdminSimpleVo(Long missionId, Integer missionTh, LocalDateTime missionStartDate,
                                Integer attendanceCount, Integer lateAttendanceCount, MissionStatus missionStatus) {
        this.missionId = missionId;
        this.missionTh = missionTh;
        this.missionStartDate = missionStartDate;
        this.attendanceCount = attendanceCount;
        this.lateAttendanceCount = lateAttendanceCount;
        this.missionStatus = missionStatus;
    }
}
