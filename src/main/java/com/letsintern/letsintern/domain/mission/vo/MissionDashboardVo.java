package com.letsintern.letsintern.domain.mission.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MissionDashboardVo {

    private Long id;

    private Integer th;

    private String title;

    private String contents;

    private String guide;

    private LocalDateTime endDate;

    @Builder
    public MissionDashboardVo(Long id, Integer th, String title, String contents, String guide, LocalDateTime endDate) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.contents = contents;
        this.guide = guide;
        this.endDate = endDate;
    }

}
