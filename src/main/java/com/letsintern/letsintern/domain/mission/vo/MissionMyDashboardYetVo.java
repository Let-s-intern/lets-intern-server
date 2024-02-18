package com.letsintern.letsintern.domain.mission.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionMyDashboardYetVo {

    private Long id;

    private Integer th;

    private String title;

    private String contents;

    private String guide;

    @Builder
    public MissionMyDashboardYetVo(Long id, Integer th, String title, String contents, String guide) {
        this.id = id;
        this.th = th;
        this.title = title;
        this.contents = contents;
        this.guide = guide;
    }

}
