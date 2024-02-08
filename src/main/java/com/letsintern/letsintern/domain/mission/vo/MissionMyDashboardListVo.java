package com.letsintern.letsintern.domain.mission.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionMyDashboardListVo {

    private Long id;

    private Integer th;

    private String title;

    @Builder
    public MissionMyDashboardListVo(Long id, Integer th, String title) {
        this.id = id;
        this.th = th;
        this.title = title;
    }
}
