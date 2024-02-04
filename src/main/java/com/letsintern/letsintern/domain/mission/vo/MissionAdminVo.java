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

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isRefunded;

    private List<Long> contentsIdList;

    private Integer attendanceCount;

    private Boolean isVisible;

    private MissionStatus status;

    public MissionAdminVo(Long id, Integer th, String title, LocalDateTime startDate, LocalDateTime endDate,
                          Boolean isRefunded, String contentsListStr, Integer attendanceCount, Boolean isVisible, MissionStatus status) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isRefunded = isRefunded;
        if(contentsListStr != null && !contentsListStr.equals("")) this.contentsIdList = StringUtils.stringToList(contentsListStr);
        this.attendanceCount = attendanceCount;
        this.isVisible = isVisible;
        this.status = status;
    }
}
