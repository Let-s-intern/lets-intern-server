package com.letsintern.letsintern.domain.mission.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MissionIdResponse {

    private Long missionId;

    @Builder
    private MissionIdResponse(Long missionId) {
        this.missionId = missionId;
    }

    public static MissionIdResponse from(Long missionId) {
        return MissionIdResponse.builder()
                .missionId(missionId)
                .build();
    }
}
