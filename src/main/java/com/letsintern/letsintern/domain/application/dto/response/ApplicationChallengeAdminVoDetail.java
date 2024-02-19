package com.letsintern.letsintern.domain.application.dto.response;

import com.letsintern.letsintern.domain.mission.vo.MissionAdminApplicationVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ApplicationChallengeAdminVoDetail {

    private String applyMotive;

    private List<MissionAdminApplicationVo> missionList;

    @Builder
    private ApplicationChallengeAdminVoDetail(String applyMotive, List<MissionAdminApplicationVo> missionList) {
        this.applyMotive = applyMotive;
        this.missionList = missionList;
    }

    public static ApplicationChallengeAdminVoDetail of(String applyMotive, List<MissionAdminApplicationVo> missionList) {
        return ApplicationChallengeAdminVoDetail.builder()
                .applyMotive(applyMotive)
                .missionList(missionList)
                .build();
    }
}
