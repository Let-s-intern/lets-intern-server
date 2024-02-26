package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionMyDashboardDoneVo {

    private Long id;

    private Integer th;

    private MissionType type;

    private String title;

    private String contents;

    private String essentialContentsLink;

    private String additionalContentsLink;

    private String limitedContentsLink;

    private String missionComments;

    private Long attendanceId;

    private AttendanceStatus attendanceStatus;

    private AttendanceResult attendanceResult;

    private String attendanceLink;

    private String attendanceComments;

    @Builder
    public MissionMyDashboardDoneVo(Long id, Integer th, MissionType type, String title, String contents, String essentialContentsLink, String additionalContentsLink,
                                    String limitedContentsLink, String missionComments, Attendance attendance) {
        this.id = id;
        this.th = th;
        this.type = type;
        this.title = title;
        this.contents = contents;
        this.essentialContentsLink = essentialContentsLink;
        this.additionalContentsLink = additionalContentsLink;
        if(attendance != null) {
            if(attendance.getResult().equals(AttendanceResult.PASS)) {
                this.limitedContentsLink = limitedContentsLink;
                this.missionComments = missionComments;
            }
            this.attendanceId = attendance.getId();
            this.attendanceStatus = attendance.getStatus();
            this.attendanceResult = attendance.getResult();
            this.attendanceLink = attendance.getLink();
            this.attendanceComments = attendance.getComments();
        } else {
            attendanceStatus = AttendanceStatus.ABSENT;
        }
    }
}
