package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionMyDashboardVo {

    private Long id;

    private Integer th;

    private String title;

    private String contents;

    private String guide;

    private String template;

    private LocalDateTime endDate;

    private boolean isAttended;

    private String essentialContentsLink;

    private String attendanceLink;

    @Builder
    public MissionMyDashboardVo(Long id, Integer th, String title, String contents, String guide, String template, LocalDateTime endDate,
                                String essentialContentsLink, Attendance attendance) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.contents = contents;
        this.guide = guide;
        this.template = template;
        this.endDate = endDate;
        if(essentialContentsLink != null) this.essentialContentsLink = essentialContentsLink;

        if(attendance != null) {
            this.isAttended = true;
            this.attendanceLink = attendance.getLink();
        } else {
            this.isAttended = false;
        }
    }

}
