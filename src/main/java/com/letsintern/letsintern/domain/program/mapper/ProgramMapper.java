package com.letsintern.letsintern.domain.program.mapper;

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

    public Program toEntity(ProgramCreateRequestDTO programCreateRequestDTO, ZoomMeetingCreateResponse zoomMeetingCreateResponse) {
        return Program.of(programCreateRequestDTO, zoomMeetingCreateResponse);
    }

    public ProgramIdResponseDTO toProgramIdResponseDTO(Long programId) {
        return ProgramIdResponseDTO.from(programId);
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

    public ProgramEntireDashboardResponse toProgramEntireDashboardResponse(ApplicationEntireDashboardVo myDashboard, Page<ApplicationEntireDashboardVo> dashboardList) {
        return ProgramEntireDashboardResponse.of(myDashboard, dashboardList);
    }
}
