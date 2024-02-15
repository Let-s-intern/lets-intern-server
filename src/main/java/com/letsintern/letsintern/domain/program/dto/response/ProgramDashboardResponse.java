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

    private String userName;
    private MissionDashboardVo dailyMission;

    private List<Notice> noticeList;

    private List<MissionDashboardListVo> missionList;

    private Integer currentRefund;

    private Integer totalRefund;

    private Integer finalHeadCount;

    private Integer yesterdayHeadCount;

    @Builder
    private ProgramDashboardResponse(String userName, MissionDashboardVo dailyMission, Page<Notice> noticeList, List<MissionDashboardListVo> missionList,
                                     Integer currentRefund, Integer totalRefund, Integer finalHeadCount, Integer yesterdayHeadCount) {
        this.userName = userName;
        this.dailyMission = dailyMission;
        this.noticeList = (noticeList.hasContent()) ? noticeList.getContent() : new ArrayList<>();
        this.missionList = missionList;
        this.currentRefund = currentRefund;
        this.totalRefund = totalRefund;
        this.finalHeadCount = finalHeadCount;
        if(dailyMission.getTh() >= 2 && dailyMission.getTh() <= 14) this.yesterdayHeadCount = yesterdayHeadCount;
    }

    public static ProgramDashboardResponse of(String userName, MissionDashboardVo dailyMission, Page<Notice> noticeList, List<MissionDashboardListVo> missionList,
                                              Integer currentRefund, Integer totalRefund, Integer finalHeadCount, Integer yesterdayHeadCount) {
        return ProgramDashboardResponse.builder()
                .userName(userName)
                .dailyMission(dailyMission)
                .noticeList(noticeList)
                .missionList(missionList)
                .currentRefund(currentRefund)
                .totalRefund(totalRefund)
                .finalHeadCount(finalHeadCount)
                .yesterdayHeadCount(yesterdayHeadCount)
                .build();
    }
}
