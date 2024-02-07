package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.mission.domain.MissionStatus;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MissionAdminVo {

    private Long id;

    private Integer th;

    private String title;

    private String contents;

    private String guide;

    private String template;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isRefunded;

    private List<Long> contentsIdList;

    private Integer attendanceCount;

    private Boolean isVisible;

    private MissionStatus status;

    public MissionAdminVo(Long id, Integer th, String title, String contents, String guide, String template, LocalDateTime startDate, LocalDateTime endDate,
                          Boolean isRefunded, String contentsListStr, Integer attendanceCount, Boolean isVisible, MissionStatus status) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.contents = contents;
        this.guide = guide;
        this.template = template;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isRefunded = isRefunded;
        if(contentsListStr != null && !contentsListStr.equals("")) this.contentsIdList = StringUtils.stringToList(contentsListStr);
        this.attendanceCount = attendanceCount;
        this.isVisible = isVisible;
        this.status = status;
    }
}
