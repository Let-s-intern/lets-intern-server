package com.letsintern.letsintern.domain.mission.dto.response;

import com.letsintern.letsintern.domain.mission.vo.MissionAdminSimpleVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MissionAdminSimpleListResponse {

    private Integer finalHeadCount;
    private List<MissionAdminSimpleVo> missionList;

    @Builder
    private MissionAdminSimpleListResponse(Integer finalHeadCount, List<MissionAdminSimpleVo> missionList) {
        this.finalHeadCount = finalHeadCount;
        this.missionList = missionList;
    }

    public static MissionAdminSimpleListResponse of(Integer finalHeadCount, List<MissionAdminSimpleVo> missionList) {
        return MissionAdminSimpleListResponse.builder()
                .finalHeadCount(finalHeadCount)
                .missionList(missionList)
                .build();
    }
}
