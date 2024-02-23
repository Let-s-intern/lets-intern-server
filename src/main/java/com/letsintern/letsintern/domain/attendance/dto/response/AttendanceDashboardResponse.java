package com.letsintern.letsintern.domain.attendance.dto.response;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceDashboardVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AttendanceDashboardResponse {

    private String name;

    private ApplicationWishJob wishJob;

    private String introduction;

    private boolean isMine;

    private List<AttendanceDashboardVo> attendanceList;

    @Builder
    private AttendanceDashboardResponse(String name, ApplicationWishJob wishJob, boolean isMine, String introduction, List<AttendanceDashboardVo> attendanceList) {
        this.name = name;
        this.wishJob = wishJob;
        this.isMine = isMine;
        this.introduction = introduction;
        this.attendanceList = attendanceList;
    }

    public static AttendanceDashboardResponse of(String name, ApplicationWishJob wishJob, boolean isMine, String introduction, List<AttendanceDashboardVo> attendanceList) {
        return AttendanceDashboardResponse.builder()
                .name(name)
                .wishJob(wishJob)
                .isMine(isMine)
                .introduction(introduction)
                .attendanceList(attendanceList)
                .build();
    }
}
