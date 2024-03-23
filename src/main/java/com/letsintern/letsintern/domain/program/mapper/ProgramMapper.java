package com.letsintern.letsintern.domain.program.mapper;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.vo.ApplicationEntireDashboardVo;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardListVo;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardVo;
import com.letsintern.letsintern.domain.mission.vo.MissionMyDashboardVo;
import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.*;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import com.letsintern.letsintern.domain.program.vo.UserProgramVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgramMapper {

    public Program toEntity(ProgramCreateRequestDTO programCreateRequestDTO, String mentorPassword, ZoomMeetingCreateResponse zoomMeetingCreateResponse) {
        return Program.of(programCreateRequestDTO, mentorPassword, zoomMeetingCreateResponse);
    }

    public ProgramAdminEmailResponse toProgramAdminEmailResponse(List<String> emailAddressList, String emailContents) {
        return ProgramAdminEmailResponse.of(emailAddressList, emailContents);
    }

    public ProgramIdResponseDTO toProgramIdResponseDTO(Long programId) {
        return ProgramIdResponseDTO.from(programId);
    }

    public ProgramMentorPasswordResponse toProgramMentorPasswordResponse(String mentorPassword) {
        return ProgramMentorPasswordResponse.from(mentorPassword);
    }

    public LetsChatPriorSessionNoticeResponse toLetsChatPriorSessionNoticeResponse(Program program, List<String> applyMotiveList, List<String> preQuestionList) {
        return LetsChatPriorSessionNoticeResponse.of(program, applyMotiveList, preQuestionList);
    }

    public LetsChatAfterSessionNoticeResponse toLetsChatAfterSessionNoticeResponse(List<String> reviewList) {
        return LetsChatAfterSessionNoticeResponse.from(reviewList);
    }

    public ProgramListDTO toProgramListDTO(Page<ProgramThumbnailVo> programList) {
        return ProgramListDTO.from(programList);
    }

    public UserProgramVoResponse toUserProgramVoResponse(Page<UserProgramVo> userProgramList) {
        return UserProgramVoResponse.from(userProgramList);
    }

    public ProgramDashboardResponse toProgramDashboardResponse(String userName, MissionDashboardVo dailyMission, Page<Notice> noticeList, List<MissionDashboardListVo> missionList,
                                                               Integer totalRefund, Integer finalHeadCount, Integer yesterdayHeadCount) {
        return ProgramDashboardResponse.of(userName, dailyMission, noticeList, missionList, totalRefund, finalHeadCount, yesterdayHeadCount);
    }

    public ProgramMyDashboardResponse toProgramMyDashboardResponse(MissionMyDashboardVo dailyMission, List<MissionDashboardListVo> missionList) {
        return ProgramMyDashboardResponse.of(dailyMission, missionList);
    }

    public ProgramEntireDashboardResponse toProgramEntireDashboardResponse(Page<ApplicationEntireDashboardVo> dashboardList, List<ApplicationWishJob> wishJobList) {
        return ProgramEntireDashboardResponse.of(dashboardList, wishJobList);
    }


}
