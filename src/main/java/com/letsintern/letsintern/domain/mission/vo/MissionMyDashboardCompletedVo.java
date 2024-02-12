package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionMyDashboardCompletedVo {

    private Long id;

    private Integer th;

    private String title;

    private String contents;

    private Long essentialContentsId;

    private Long additionalContentsId;

    private Long limitedContentsId;

    private AttendanceStatus attendanceStatus;

    private String attendanceLink;

    private String attendanceComments;

    @Builder
    public MissionMyDashboardCompletedVo(Long id, Integer th, String title, String contents,
                                         Long essentialContentsId, Long additionalContentsId, Long limitedContentsId, Attendance attendance) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.contents = contents;
        this.essentialContentsId = essentialContentsId;
        this.additionalContentsId = additionalContentsId;
        this.limitedContentsId = limitedContentsId;
        this.attendanceStatus = attendance.getStatus();
        this.attendanceLink = attendance.getLink();
        this.attendanceComments = attendance.getComments();
    }
}
