package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MissionMyDashboardVo {

    private Long id;

    private Integer th;

    private String title;

    private String contents;

    private String guide;

    private String template;

    private LocalDateTime endDate;


    private List<Long> contentsIdList;


    private boolean isAttended;

    private String link;

    @Builder
    public MissionMyDashboardVo(Long id, Integer th, String title, String contents, String guide, String template, LocalDateTime endDate,
                                String contentsListStr, Attendance attendance) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.contents = contents;
        this.guide = guide;
        this.template = template;
        this.endDate = endDate;
        this.contentsIdList = StringUtils.stringToList(contentsListStr);

        if(attendance != null) {
            this.isAttended = true;
            this.link = attendance.getLink();
        } else {
            this.isAttended = false;
        }
    }

}
