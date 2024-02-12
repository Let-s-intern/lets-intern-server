package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.mission.domain.MissionStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionAdminVo {

    private Long id;

    private Integer th;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isRefunded;

    private ContentsTopic essentialContentsTopic;

    private Integer attendanceCount;

    private Boolean isVisible;

    private MissionStatus status;

    public MissionAdminVo(Long id, Integer th, String title, LocalDateTime startDate, LocalDateTime endDate,
                          Boolean isRefunded, ContentsTopic essentialContentsTopic, Integer attendanceCount, Boolean isVisible, MissionStatus status) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isRefunded = isRefunded;
        if(essentialContentsTopic != null) this.essentialContentsTopic = essentialContentsTopic;
        this.attendanceCount = attendanceCount;
        this.isVisible = isVisible;
        this.status = status;
    }
}
