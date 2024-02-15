package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionMyDashboardCompletedVo {

    private Long id;

    private Integer th;

    private String title;

    private String contents;

    private String essentialContentsLink;

    private String additionalContentsLink;

    private String limitedContentsLink;

    private AttendanceStatus attendanceStatus;

    private String attendanceLink;

    private String attendanceComments;

    @Builder
    public MissionMyDashboardCompletedVo(Long id, Integer th, MissionStatus status, String title, String contents,
                                         String essentialContentsLink, String additionalContentsLink, String limitedContentsLink, Attendance attendance) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.contents = contents;
        this.essentialContentsLink = essentialContentsLink;
        this.additionalContentsLink = additionalContentsLink;
        if(status.equals(MissionStatus.DONE) && (attendance != null && attendance.getStatus().equals(AttendanceStatus.PASSED))) {
            this.limitedContentsLink = limitedContentsLink;
        }
        if(attendance != null) {
            this.attendanceStatus = attendance.getStatus();
            this.attendanceLink = attendance.getLink();
            this.attendanceComments = attendance.getComments();
        }
    }
}
