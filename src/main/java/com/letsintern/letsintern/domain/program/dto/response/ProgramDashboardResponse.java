package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.mission.vo.MissionDashboardListVo;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardVo;
import com.letsintern.letsintern.domain.notice.domain.Notice;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProgramDashboardResponse {

    private MissionDashboardVo todayMission;

    private List<Notice> noticeList;

    private List<MissionDashboardListVo> missionList;

    private Integer currentRefund;

    private Integer totalRefund;

    @Builder
    private ProgramDashboardResponse(MissionDashboardVo todayMission, Page<Notice> noticeList,
                                     List<MissionDashboardListVo> missionList, Integer currentRefund, Integer totalRefund) {
        this.todayMission = todayMission;
        this.noticeList = (noticeList.hasContent()) ? noticeList.getContent() : new ArrayList<>();
        this.missionList = missionList;
        this.currentRefund = currentRefund;
        this.totalRefund = totalRefund;
    }

    public static ProgramDashboardResponse of(MissionDashboardVo todayMission, Page<Notice> noticeList,
                                              List<MissionDashboardListVo> missionList, Integer currentRefund, Integer totalRefund) {
        return ProgramDashboardResponse.builder()
                .todayMission(todayMission)
                .noticeList(noticeList)
                .missionList(missionList)
                .currentRefund(currentRefund)
                .totalRefund(totalRefund)
                .build();
    }
}
