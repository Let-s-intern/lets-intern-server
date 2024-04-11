package com.letsintern.letsintern.domain.program.mapper;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.vo.ApplicationEntireDashboardVo;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardListVo;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardVo;
import com.letsintern.letsintern.domain.mission.vo.MissionMyDashboardVo;
import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.dto.response.*;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgramMapper {

    public <T> ProgramListResponseDto<?> toProgramListResponseDto(Page<T> programThumbnailVos) {
        PageInfo pageInfo = PageInfo.of(programThumbnailVos);
        return ProgramListResponseDto.of(programThumbnailVos.getContent(), pageInfo);
    }

    public <T> ProgramDetailResponseDto<?> toProgramDetailResponseDto(T programDetailVo,
                                                                      boolean existApplication,
                                                                      List<FaqVo> faqList,
                                                                      List<ReviewVo> reviewList,
                                                                      List<ApplicationWishJob> wishJobList) {
        return ProgramDetailResponseDto.of(programDetailVo, existApplication, faqList, reviewList, wishJobList);
    }

    public <T> BaseProgramResponseDto<?> toBaseProgramResponseDto(T program) {
        return BaseProgramResponseDto.of(program);
    }

    public ProgramCountResponseDto toProgramCountResponseDto(Long count) {
        return ProgramCountResponseDto.of(count);
    }

    public ProgramAdminEmailResponseDto toProgramAdminEmailResponse(List<String> emailAddressList, String emailContents) {
        return ProgramAdminEmailResponseDto.of(emailAddressList, emailContents);
    }

    public ProgramDashboardResponseDto toProgramDashboardResponse(User user,
                                                                  MissionDashboardVo dailyMission,
                                                                  Page<Notice> noticeList,
                                                                  List<MissionDashboardListVo> missionList,
                                                                  Program program,
                                                                  Integer currentRefund,
                                                                  Integer yesterdayHeadCount) {
        return ProgramDashboardResponseDto.of(user, dailyMission, noticeList, missionList, program, currentRefund, yesterdayHeadCount);
    }

    public ProgramMyDashboardResponseDto toProgramMyDashboardResponse(MissionMyDashboardVo dailyMission, List<MissionDashboardListVo> missionList, Program program) {
        boolean isDone = program.getEndDate().isBefore(LocalDateTime.now());
        return ProgramMyDashboardResponseDto.of(dailyMission, missionList, isDone);
    }

    public ProgramEntireDashboardResponseDto toProgramEntireDashboardResponse(Page<ApplicationEntireDashboardVo> dashboardList, List<ApplicationWishJob> wishJobList) {
        return ProgramEntireDashboardResponseDto.of(dashboardList, wishJobList);
    }
}
