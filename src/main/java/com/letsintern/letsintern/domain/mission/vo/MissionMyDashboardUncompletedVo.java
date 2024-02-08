package com.letsintern.letsintern.domain.mission.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionMyDashboardUncompletedVo {

    private Long id;

    private String title;

    private String contents;

    private String guide;

    @Builder
    public MissionMyDashboardUncompletedVo(Long id, String title, String contents, String guide) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.guide = guide;
    }

}
