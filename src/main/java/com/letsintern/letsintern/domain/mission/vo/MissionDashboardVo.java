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

    private LocalDateTime endDate;

    @Builder
    public MissionDashboardVo(Long id, Integer th, String title, String contents, LocalDateTime endDate) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.contents = contents;
        this.endDate = endDate;
    }

}
