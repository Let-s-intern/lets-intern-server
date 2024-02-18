package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionMyDashboardDoneVo {

    private Long id;

    private Integer th;

    private String title;

    private String contents;

    private String essentialContentsLink;

    private String additionalContentsLink;

    private String limitedContentsLink;

    private String missionComments;

    private AttendanceStatus attendanceStatus;

    private AttendanceResult attendanceResult;

    private String attendanceLink;

    private String attendanceComments;

    @Builder
    public MissionMyDashboardDoneVo(Long id, Integer th, MissionStatus status, String title, String contents,
                                    String essentialContentsLink, String additionalContentsLink, String limitedContentsLink, String missionComments, Attendance attendance) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.contents = contents;
        this.essentialContentsLink = essentialContentsLink;
        this.additionalContentsLink = additionalContentsLink;
        if((status.equals(MissionStatus.CHECK_DONE) || status.equals(MissionStatus.REFUND_DONE))
                && (attendance != null && attendance.getStatus().equals(AttendanceStatus.PRESENT) && attendance.getResult().equals(AttendanceResult.PASS))) {
            this.limitedContentsLink = limitedContentsLink;
            this.missionComments = missionComments;
        }
        if(attendance != null) {
            this.attendanceStatus = attendance.getStatus();
            this.attendanceResult = attendance.getResult();
            this.attendanceLink = attendance.getLink();
            this.attendanceComments = attendance.getComments();
        } else {
            attendanceStatus = AttendanceStatus.ABSENT;
        }
    }
}
