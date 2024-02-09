package com.letsintern.letsintern.domain.mission.dto.response;

import com.letsintern.letsintern.domain.mission.vo.MissionMyDashboardListVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MissionMyDashboardListResponse {

    private List<MissionMyDashboardListVo> missionList;

    @Builder
    private MissionMyDashboardListResponse(List<MissionMyDashboardListVo> missionList) {
        this.missionList = missionList;
    }

    public static MissionMyDashboardListResponse from(List<MissionMyDashboardListVo> missionList) {
        return MissionMyDashboardListResponse.builder()
                .missionList(missionList)
                .build();
    }
}
