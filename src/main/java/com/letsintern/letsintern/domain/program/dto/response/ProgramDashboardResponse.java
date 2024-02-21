package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.mission.domain.MissionType;
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
                                     Integer totalRefund, Integer finalHeadCount, Integer yesterdayHeadCount) {
        int currentRefund = 0;
        for(MissionDashboardListVo mission : missionList) {
            if(mission.getMissionType().equals(MissionType.REFUND) && mission.getAttendanceStatus().equals(AttendanceStatus.PRESENT) && !mission.getAttendanceResult().equals(AttendanceResult.WRONG)) {
                currentRefund += mission.getMissionRefund();
            }
        }

        this.userName = userName;
        this.dailyMission = dailyMission;
        this.noticeList = (noticeList.hasContent()) ? noticeList.getContent() : new ArrayList<>();
        this.missionList = missionList;
        this.currentRefund = currentRefund;
        this.totalRefund = totalRefund;
        this.finalHeadCount = finalHeadCount;
        if(dailyMission != null && (dailyMission.getTh() >= 2 && dailyMission.getTh() <= 14)) this.yesterdayHeadCount = yesterdayHeadCount;
    }

    public static ProgramDashboardResponse of(String userName, MissionDashboardVo dailyMission, Page<Notice> noticeList, List<MissionDashboardListVo> missionList,
                                              Integer totalRefund, Integer finalHeadCount, Integer yesterdayHeadCount) {
        return ProgramDashboardResponse.builder()
                .userName(userName)
                .dailyMission(dailyMission)
                .noticeList(noticeList)
                .missionList(missionList)
                .totalRefund(totalRefund)
                .finalHeadCount(finalHeadCount)
                .yesterdayHeadCount(yesterdayHeadCount)
                .build();
    }
}
