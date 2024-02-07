package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.mission.vo.MissionDashboardListVo;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardVo;
import com.letsintern.letsintern.domain.mission.vo.MissionMyDashboardVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProgramMyDashboardResponse {

    private MissionMyDashboardVo dailyMission;

    private List<MissionDashboardListVo> missionList;

    @Builder
    private ProgramMyDashboardResponse(MissionMyDashboardVo dailyMission, List<MissionDashboardListVo> missionList) {
        this.dailyMission = dailyMission;
        this.missionList = missionList;
    }

    public static ProgramMyDashboardResponse of(MissionMyDashboardVo dailyMission, List<MissionDashboardListVo> missionList) {
        return ProgramMyDashboardResponse.builder()
                .dailyMission(dailyMission)
                .missionList(missionList)
                .build();
    }



}
