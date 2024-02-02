package com.letsintern.letsintern.domain.mission.vo;

import com.letsintern.letsintern.domain.mission.domain.MissionStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionAdminVo {

    private Long id;

    private String title;

    private Integer th;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isRefunded;

    private Integer attendanceCount;

    private MissionStatus status;

    public MissionAdminVo(Long id, String title, Integer th, LocalDateTime startDate, LocalDateTime endDate,
                          Boolean isRefunded, Integer attendanceCount, MissionStatus status) {
        this.id = id;
        this.title = title;
        this.th = th;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isRefunded = isRefunded;
        this.attendanceCount = attendanceCount;
        this.status = status;
    }
}
