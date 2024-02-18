package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionMyDashboardAbsentVo {

    private Long id;

    private Integer th;

    private String title;

    private String contents;

    private String guide;

    private String template;

    private String essentialContentsLink;

    private String additionalContentsLink;

    private AttendanceStatus attendanceStatus;

    private AttendanceResult attendanceResult;

    private String attendanceComments;

    @Builder
    public MissionMyDashboardAbsentVo(Long id, Integer th, String title, String contents, String guide, String template,
                                      String essentialContentsLink, String additionalContentsLink, Attendance attendance) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.contents = contents;
        this.guide = guide;
        this.template = template;
        this.essentialContentsLink = essentialContentsLink;
        this.additionalContentsLink = additionalContentsLink;

        if(attendance == null) attendanceStatus = AttendanceStatus.ABSENT;
        else {
            attendanceStatus = attendance.getStatus();
            attendanceResult = attendance.getResult();
            attendanceComments = attendance.getComments();
        }
    }
}
