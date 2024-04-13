package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.mission.vo.MissionDashboardListVo;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardVo;
import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Builder(access = AccessLevel.PRIVATE)
public record ProgramDashboardResponseDto(
        String userName,
        MissionDashboardVo dailyMission,
        List<Notice> noticeList,
        List<MissionDashboardListVo> missionList,
        Integer currentRefund,
        Integer totalRefund,
        Integer finalHeadCount,
        Integer yesterdayHeadCount,
        Boolean isDone
) {
    public static ProgramDashboardResponseDto of(User user,
                                                 MissionDashboardVo dailyMission,
                                                 Page<Notice> noticeList,
                                                 List<MissionDashboardListVo> missionList,
                                                 Program program,
                                                 Integer currentRefund,
                                                 Integer yesterdayHeadCount) {
        return ProgramDashboardResponseDto.builder()
                .userName(user.getName())
                .dailyMission(dailyMission)
                .noticeList(noticeList.getContent())
                .missionList(missionList)
                .totalRefund(program.getPayment().getFeeRefund())
                .currentRefund(currentRefund)
                .finalHeadCount(program.getHeadcount())
                .yesterdayHeadCount(yesterdayHeadCount)
                .isDone(program.getEndDate().isBefore(LocalDateTime.now()))
                .build();
    }
}
