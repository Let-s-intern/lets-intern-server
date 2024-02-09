package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MissionMyDashboardCompletedVo {

    private Long id;

    private Integer th;

    private String title;

    private String contents;

    private List<Long> contentsIdList;

    private AttendanceStatus attendanceStatus;

    private String attendanceLink;

    private String attendanceComments;

    @Builder
    public MissionMyDashboardCompletedVo(Long id, Integer th, String title, String contents, String contentsListStr, Attendance attendance) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.contents = contents;
        this.contentsIdList = StringUtils.stringToList(contentsListStr);
        this.attendanceStatus = attendance.getStatus();
        this.attendanceLink = attendance.getLink();
        this.attendanceComments = attendance.getComments();
    }
}
