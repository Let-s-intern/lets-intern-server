package com.letsintern.letsintern.domain.mission.dto.response;

import com.letsintern.letsintern.domain.mission.vo.MissionAdminSimpleVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Getter
public class MissionAdminSimpleListResponse {

    private Integer finalHeadCount;
    private Integer currentTh = 0;
    private List<MissionAdminSimpleVo> missionList;

    @Builder
    private MissionAdminSimpleListResponse(Integer finalHeadCount, LocalDateTime startDate, List<MissionAdminSimpleVo> missionList) {
        this.finalHeadCount = finalHeadCount;

        LocalDate today = LocalDate.now();
        int diffDays = Period.between(LocalDate.from(startDate), today).getDays() + 1;
        if(diffDays >= 1 && diffDays <= 14) this.currentTh = diffDays;

        this.missionList = missionList;
    }

    public static MissionAdminSimpleListResponse of(Integer finalHeadCount, LocalDateTime startDate, List<MissionAdminSimpleVo> missionList) {
        return MissionAdminSimpleListResponse.builder()
                .finalHeadCount(finalHeadCount)
                .startDate(startDate)
                .missionList(missionList)
                .build();
    }
}
