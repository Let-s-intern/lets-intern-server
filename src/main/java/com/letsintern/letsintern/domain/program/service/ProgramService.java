package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.attendance.repository.AttendanceRepository;
import com.letsintern.letsintern.domain.mission.helper.MissionHelper;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardVo;
import com.letsintern.letsintern.domain.notice.helper.NoticeHelper;
import com.letsintern.letsintern.domain.program.domain.MailType;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.dto.request.LetsChatMentorPasswordRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.*;
import com.letsintern.letsintern.domain.program.exception.ProgramMentorPasswordMismatch;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.domain.UserRole;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramHelper programHelper;
    private final ProgramMapper programMapper;

    private final ProgramRepository programRepository;

    private final ApplicationHelper applicationHelper;
    private final ApplicationRepository applicationRepository;
    private final MissionHelper missionHelper;
    private final NoticeHelper noticeHelper;
    private final AttendanceRepository attendanceRepository;

    public Long getDoneProgramCount() {
        return programRepository.countByStatusEquals(ProgramStatus.DONE);
    }

    private void checkMentorPasswordMatches(String programMentorPassword, String requestMentorPassword) {
        if(!Objects.equals(programMentorPassword, requestMentorPassword)) {
            throw ProgramMentorPasswordMismatch.EXCEPTION;
        }
    }

    @Transactional
    public ProgramIdResponseDTO createProgram(ProgramCreateRequestDTO programCreateRequestDTO) throws Exception {
        return programMapper.toProgramIdResponseDTO(programHelper.createProgram(programCreateRequestDTO));
    }

    @Transactional
    public ProgramIdResponseDTO updateProgram(Long programId, ProgramUpdateRequestDTO programUpdateRequestDTO) throws ParseException {
        return programMapper.toProgramIdResponseDTO(programHelper.updateProgram(programId, programUpdateRequestDTO));
    }

    public ProgramMentorPasswordResponse getProgramMentorPassword(Long programId) {
        return programMapper.toProgramMentorPasswordResponse(programHelper.getProgramMentorPassword(programId));
    }

    @Transactional
    public ProgramListDTO getProgramThumbnailList(String type, Pageable pageable) {
        return programHelper.getProgramThumbnailList(type, pageable);
    }

    @Transactional
    public AdminProgramListDTO getProgramAdminList(String type, Integer th, Pageable pageable) {
        return programHelper.getAdminProgramList(type, th, pageable);
    }

    public UserProgramVoResponse getAdminUserProgramList(Long userId, Pageable pageable) {
        return programMapper.toUserProgramVoResponse(programHelper.getAdminUserProgramList(userId, pageable));
    }

    public ProgramDetailDTO getProgramDetailDTO(Long programId, PrincipalDetails principalDetails) {
        if(principalDetails != null) {
            final Long userId = principalDetails.getUser().getId();
            return programHelper.getProgramDetailVo(programId, userId);
        }
        else return programHelper.getProgramDetailVo(programId, null);
    }

    public Program getProgram(Long programId) {
        return programHelper.getExistingProgram(programId);
    }

    public void deleteProgram(Long programId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });
        programRepository.delete(program);
    }

    @Transactional
    public void saveFinalHeadCount(Long programId) {
        programHelper.saveFinalHeadCount(programId);
    }


    public ProgramAdminEmailResponse getEmailTemplate(Long programId, MailType mailType) {
        final Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        return programMapper.toProgramAdminEmailResponse(
                applicationHelper.getApplicationEmailListOfProgramIdAndMailType(program, mailType),
                programHelper.createChallengeProgramEmailByMailType(program, mailType)
        );
    }

    @Transactional(readOnly = true)
    public ProgramDashboardResponse getProgramDashboard(Long programId, PrincipalDetails principalDetails, Pageable pageable) {
        final Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        final User user = principalDetails.getUser();
        if(!user.getRole().equals(UserRole.ROLE_ADMIN)) {
            final Application application = applicationRepository.findByProgramIdAndUserId(programId, user.getId());
            if(application == null) throw  ApplicationNotFound.EXCEPTION;
        }

        MissionDashboardVo dailyMission = missionHelper.getDailyMission(program.getId(), program.getStartDate());
        Integer yesterdayHeadCount = (dailyMission == null) ? null : attendanceRepository.countAllByMissionProgramIdAndMissionThAndStatusAndResult(programId, dailyMission.getTh() - 1, AttendanceStatus.PRESENT, AttendanceResult.PASS);

        return programMapper.toProgramDashboardResponse(
                user.getName(),
                dailyMission,
                noticeHelper.getNoticeList(programId, pageable),
                missionHelper.getMissionDashboardList(programId, user.getId()),
                program.getFeeRefund(),
                program.getFinalHeadCount(),
                yesterdayHeadCount
        );
    }

    @Transactional(readOnly = true)
    public ProgramMyDashboardResponse getProgramMyDashboard(Long programId, PrincipalDetails principalDetails) {
        final Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        final User user = principalDetails.getUser();
        if(!user.getRole().equals(UserRole.ROLE_ADMIN)) {
            final Application application = applicationRepository.findByProgramIdAndUserId(programId, user.getId());
            if(application == null) throw  ApplicationNotFound.EXCEPTION;
        }

        return programMapper.toProgramMyDashboardResponse(
                missionHelper.getDailyMissionDetail(program.getId(), program.getStartDate(), user.getId()),
                missionHelper.getMissionDashboardList(program.getId(), user.getId())
        );
    }

    public ProgramEntireDashboardResponse getProgramEntireDashboard(Long programId, ApplicationWishJob applicationWishJob, PrincipalDetails principalDetails, Pageable pageable) {
        final Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        final User user = principalDetails.getUser();

        return programMapper.toProgramEntireDashboardResponse(
                applicationHelper.getDashboardList(program.getId(), applicationWishJob, user.getId(), pageable),
                ApplicationWishJob.getApplicationWishJobListByProgramTopic(program.getTopic())
        );
    }

    public LetsChatPriorSessionNoticeResponse getLetsChatPriorSessionNotice(Long programId, LetsChatMentorPasswordRequestDTO letsChatMentorPasswordRequestDTO) {
        final Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        checkMentorPasswordMatches(program.getMentorPassword(), letsChatMentorPasswordRequestDTO.getMentorPassword());

        return programHelper.getLetsChatPriorSessionNotice(program);
    }

    public LetsChatAfterSessionNoticeResponse getLetsChatAfterSessionNotice(Long programId, LetsChatMentorPasswordRequestDTO letsChatMentorPasswordRequestDTO) {
        final Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        checkMentorPasswordMatches(program.getMentorPassword(), letsChatMentorPasswordRequestDTO.getMentorPassword());

        return programHelper.getLetsChatAfterSessionNotice(program.getId());
    }
}
