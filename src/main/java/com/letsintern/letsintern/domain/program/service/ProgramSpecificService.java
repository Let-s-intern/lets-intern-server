package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.application.vo.ApplicationEntireDashboardVo;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.attendance.helper.AttendanceHelper;
import com.letsintern.letsintern.domain.mission.helper.MissionHelper;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardListVo;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardVo;
import com.letsintern.letsintern.domain.mission.vo.MissionMyDashboardVo;
import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.notice.helper.NoticeHelper;
import com.letsintern.letsintern.domain.program.domain.*;
import com.letsintern.letsintern.domain.program.dto.response.*;
import com.letsintern.letsintern.domain.program.helper.ChallengeHelper;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.vo.program.ProgramThumbnailVo;
import com.letsintern.letsintern.domain.program.vo.program.UserProgramVo;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ProgramSpecificService {
    private final ProgramHelper programHelper;
    private final ProgramMapper programMapper;
    private final ChallengeHelper challengeHelper;
    private final ApplicationHelper applicationHelper;
    private final MissionHelper missionHelper;
    private final NoticeHelper noticeHelper;
    private final AttendanceHelper attendanceHelper;

    public ProgramCountResponseDto getDoneProgramCount() {
        Long count = programHelper.findCountForProgramStatus(ProgramStatus.DONE);
        return programMapper.toProgramCountResponseDto(count);
    }

    public ProgramListResponseDto<?> getAdminUserProgramList(Long userId, Pageable pageable) {
        Page<UserProgramVo> userProgramVos = applicationHelper.findAllProgramByUserId(userId, pageable);
        return programMapper.toProgramListResponseDto(userProgramVos);
    }

    public ProgramAdminEmailResponseDto getEmailTemplate(Long programId, MailType mailType) {
        Program program = programHelper.findProgramOrThrow(programId);
        List<String> emailAddressList = applicationHelper.getApplicationEmailListOfProgramIdAndMailType(program, mailType);
        String emailContents = programHelper.createChallengeProgramEmailByMailType(program, mailType);
        return programMapper.toProgramAdminEmailResponse(emailAddressList, emailContents);
    }

    public void saveFinalHeadCount(Long programId) {
        Challenge challenge = challengeHelper.findChallengeOrThrow(programId);
        Integer count = applicationHelper.countAllByProgramIdAndStatus(programId, ApplicationStatus.IN_PROGRESS);
        challenge.updateFinalHeadCount(count);
    }

    public ProgramDashboardResponseDto getProgramDashboard(Long programId, PrincipalDetails principalDetails, Pageable pageable) {
        Program program = programHelper.findProgramOrThrow(programId);
        User user = principalDetails.getUser();
        applicationHelper.validateIsChallengeParticipant(user.getRole(), program.getId(), user.getId());
        MissionDashboardVo dailyMission = checkDoneProgramAndGetDailyMission(program);
        Page<Notice> noticeList = noticeHelper.getNoticeList(program.getId(), pageable);
        List<MissionDashboardListVo> missionList = missionHelper.getMissionDashboardList(program.getId(), user.getId());
        Integer currentRefund = checkDoneProgramAndGetCurrentRefund(program, missionList);
        Integer yesterdayHeadCount = checkDoneProgramAndGetYesterdayHeadCount(program, dailyMission);
        return programMapper.toProgramDashboardResponse(user, dailyMission, noticeList, missionList, program, currentRefund, yesterdayHeadCount);
    }

    public ProgramMyDashboardResponseDto getProgramMyDashboard(Long programId, PrincipalDetails principalDetails) {
        Program program = programHelper.findProgramOrThrow(programId);
        User user = principalDetails.getUser();
        applicationHelper.validateIsChallengeParticipant(user.getRole(), program.getId(), user.getId());
        MissionMyDashboardVo dailyMission = missionHelper.getDailyMissionDetail(program.getId(), program.getStartDate(), user.getId());
        List<MissionDashboardListVo> missionList = missionHelper.getMissionDashboardList(program.getId(), user.getId());
        return programMapper.toProgramMyDashboardResponse(dailyMission, missionList, program);
    }

    public ProgramEntireDashboardResponseDto getProgramEntireDashboard(Long programId, ApplicationWishJob applicationWishJob, PrincipalDetails principalDetails, Pageable pageable) {
        Program program = programHelper.findProgramOrThrow(programId);
        User user = principalDetails.getUser();
        Page<ApplicationEntireDashboardVo> dashboardList = applicationHelper.getDashboardList(program.getId(), applicationWishJob, user.getId(), pageable);
        List<ApplicationWishJob> wishJobList = ApplicationWishJob.getApplicationWishJobListByProgramTopic(((Challenge) program).getTopic());
        return programMapper.toProgramEntireDashboardResponse(dashboardList, wishJobList);
    }

    private MissionDashboardVo checkDoneProgramAndGetDailyMission(Program program) {
        boolean isDoneProgram = program.getEndDate().isBefore(LocalDateTime.now());
        if (isDoneProgram)
            return null;
        else
            return missionHelper.getDailyMission(program.getId(), program.getStartDate());
    }

    private Integer checkDoneProgramAndGetCurrentRefund(Program program, List<MissionDashboardListVo> missionList) {
        boolean isDoneProgram = program.getEndDate().isBefore(LocalDateTime.now());
        if (isDoneProgram)
            return 0;
        else
            return missionHelper.getCurrentRefund(missionList);
    }

    private Integer checkDoneProgramAndGetYesterdayHeadCount(Program program, MissionDashboardVo dailyMission) {
        boolean isDoneProgram = program.getEndDate().isBefore(LocalDateTime.now());
        if (isDoneProgram)
            return null;
        else
            return attendanceHelper.getYesterdayHeadCount(program.getId(), dailyMission.getTh() - 1, AttendanceStatus.PRESENT, AttendanceResult.PASS);
    }

    public ProgramListResponseDto<?> getProgramAdminList(ProgramRequestType type, Integer th, Pageable pageable) {
        Page<Program> programPage = programHelper.findAllProgramByTypeAndTh(type, th, pageable);
        return programMapper.toProgramListResponseDto(programPage);
    }

    public ProgramListResponseDto<?> getProgramList(Pageable pageable) {
        Page<ProgramThumbnailVo> programThumbnailVoPage = programHelper.findProgramList(pageable);
        return programMapper.toProgramListResponseDto(programThumbnailVoPage);
    }
}
