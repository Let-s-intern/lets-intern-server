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

    private Boolean isDone;

    @Builder
    private ProgramMyDashboardResponse(MissionMyDashboardVo dailyMission, List<MissionDashboardListVo> missionList, Boolean isDone) {
        this.dailyMission = dailyMission;
        this.missionList = missionList;
        this.isDone = isDone;
    }

    public static ProgramMyDashboardResponse of(MissionMyDashboardVo dailyMission, List<MissionDashboardListVo> missionList, Boolean isDone) {
        return ProgramMyDashboardResponse.builder()
                .dailyMission(dailyMission)
                .missionList(missionList)
                .isDone(isDone)
                .build();
    }



}
