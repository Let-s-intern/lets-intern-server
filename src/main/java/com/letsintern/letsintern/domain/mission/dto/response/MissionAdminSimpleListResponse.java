package com.letsintern.letsintern.domain.mission.dto.response;

import com.letsintern.letsintern.domain.mission.vo.MissionAdminSimpleVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MissionAdminSimpleListResponse {

    private List<MissionAdminSimpleVo> missionList;

    @Builder
    private MissionAdminSimpleListResponse(List<MissionAdminSimpleVo> missionList) {
        this.missionList = missionList;
    }

    public static MissionAdminSimpleListResponse from(List<MissionAdminSimpleVo> missionList) {
        return MissionAdminSimpleListResponse.builder()
                .missionList(missionList)
                .build();
    }
}
