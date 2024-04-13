package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.mission.vo.MissionDashboardListVo;
import com.letsintern.letsintern.domain.mission.vo.MissionMyDashboardVo;
import lombok.AccessLevel;
import lombok.Builder;

import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ProgramMyDashboardResponseDto(
        MissionMyDashboardVo dailyMission,
        List<MissionDashboardListVo> missionList,
        Boolean isDone
) {
    public static ProgramMyDashboardResponseDto of(MissionMyDashboardVo dailyMission,
                                                   List<MissionDashboardListVo> missionList,
                                                   Boolean isDone) {
        return ProgramMyDashboardResponseDto.builder()
                .dailyMission(dailyMission)
                .missionList(missionList)
                .isDone(isDone)
                .build();
    }
}
