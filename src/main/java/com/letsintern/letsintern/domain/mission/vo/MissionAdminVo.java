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

    private MissionStatus status;

    private ContentsTopic essentialContentsTopic;

    private Integer attendanceCount;

    private Integer finalHeadCount;


    public MissionAdminVo(Long id, Integer th, String title, LocalDateTime startDate, LocalDateTime endDate,
                          MissionStatus status, ContentsTopic essentialContentsTopic, Integer attendanceCount, Integer finalHeadCount) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        if(essentialContentsTopic != null) this.essentialContentsTopic = essentialContentsTopic;
        this.attendanceCount = attendanceCount;
        this.finalHeadCount = finalHeadCount;
    }
}
