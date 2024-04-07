package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.attendance.helper.AttendanceHelper;
import com.letsintern.letsintern.domain.mission.domain.MissionType;
import com.letsintern.letsintern.domain.mission.helper.MissionHelper;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardListVo;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardVo;
import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.notice.helper.NoticeHelper;
import com.letsintern.letsintern.domain.program.domain.*;
import com.letsintern.letsintern.domain.program.dto.request.LetsChatMentorPasswordRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.*;
import com.letsintern.letsintern.domain.program.exception.ProgramMentorPasswordMismatch;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.program.helper.ZoomMeetingApiHelper;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.domain.UserRole;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
    private final AttendanceHelper attendanceHelper;
    private final NoticeHelper noticeHelper;
    private final ZoomMeetingApiHelper zoomMeetingApiHelper;

    public Long getDoneProgramCount() {
        return programRepository.countByStatusEquals(ProgramStatus.DONE);
    }

    private void checkMentorPasswordMatches(String programMentorPassword, String requestMentorPassword) {
        if (!Objects.equals(programMentorPassword, requestMentorPassword)) {
            throw ProgramMentorPasswordMismatch.EXCEPTION;
        }
    }

    @Transactional
    public ProgramIdResponseDTO createProgram(ProgramCreateRequestDTO programCreateRequestDTO) {
        /* 이용료 프로그램 정보 입력 확인 */
        checkChargeFeeTypeForInput(programCreateRequestDTO);
        /* 보증금 프로그램 정보 입력 확인 */
        checkRefundFeeTypeForInput(programCreateRequestDTO);
        /* 챌린지 프로그램 정보 입력 확인 */
        checkChallengeProgramTypeForInput(programCreateRequestDTO);
        /* [렛츠챗/챌린지] Zoom Meeting 생성 */
        ZoomMeetingCreateResponse zoomMeetingCreateResponse = createZoomMeetingForLetsChatAndChallengeType(programCreateRequestDTO);
        /* [렛츠챗] 멘토 세션 안내 페이지용 비밀번호 생성 */
        String mentorPassword = createMentorPasswordForLetsChatType(programCreateRequestDTO);
        /* program domain 생성 및 저장 */
        Program savedProgram = createProgramAndSave(programCreateRequestDTO, zoomMeetingCreateResponse, mentorPassword);
        return programMapper.toProgramIdResponseDTO(savedProgram.getId());
    }

    public Program getProgram(Long programId) {
        return programHelper.findProgramOrThrow(programId);
    }

    @Transactional
    public ProgramIdResponseDTO updateProgram(Long programId, ProgramUpdateRequestDTO programUpdateRequestDTO) {
        Program program = programHelper.findProgramOrThrow(programId);
        ProgramStatus programStatus = programHelper.getProgramStatusForDueDate(programUpdateRequestDTO);
        String stringFaqList = programHelper.parseToFaqIdList(programUpdateRequestDTO);
        program.updateProgramInfo(programUpdateRequestDTO, programStatus, stringFaqList);
        return programMapper.toProgramIdResponseDTO(program.getId());
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
        if (principalDetails != null) {
            final Long userId = principalDetails.getUser().getId();
            return programHelper.getProgramDetailVo(programId, userId);
        } else {
            return programHelper.getProgramDetailVo(programId, null);
        }
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
        final Program program = programHelper.findProgramOrThrow(programId);
        final User user = principalDetails.getUser();
        applicationHelper.validateIsChallengeParticipant(user.getRole(), program.getId(), user.getId());

        /* 챌린지 종료 여부 */
        boolean isDoneProgram = program.getEndDate().isBefore(LocalDateTime.now());
        /* 데일리 미션 */
        MissionDashboardVo dailyMission = (isDoneProgram) ? null : missionHelper.getDailyMission(program.getId(), program.getStartDate());
        /* 공지사항 목록 */
        Page<Notice> noticeList = noticeHelper.getNoticeList(program.getId(), pageable);
        /* 전체 미션, 출석 현황 */
        List<MissionDashboardListVo> missionList = missionHelper.getMissionDashboardList(program.getId(), user.getId());
        /* 현재까지 환급금 */
        Integer currentRefund = (isDoneProgram) ? 0 : missionHelper.getCurrentRefund(missionList);
        /* 어제 미션을 정상 제출한 인원수 */
        Integer yesterdayHeadCount = (isDoneProgram) ? null : attendanceHelper.getYesterdayHeadCount(program.getId(), dailyMission.getTh() - 1, AttendanceStatus.PRESENT, AttendanceResult.PASS);

        return programMapper.toProgramDashboardResponse(
                user.getName(),
                dailyMission,
                noticeList,
                missionList,
                program.getFeeRefund(),
                currentRefund,
                program.getFinalHeadCount(),
                yesterdayHeadCount,
                isDoneProgram
        );
    }

    @Transactional(readOnly = true)
    public ProgramMyDashboardResponse getProgramMyDashboard(Long programId, PrincipalDetails principalDetails) {
        final Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);
        final User user = principalDetails.getUser();
        applicationHelper.validateIsChallengeParticipant(user.getRole(), program.getId(), user.getId());

        return programMapper.toProgramMyDashboardResponse(
                missionHelper.getDailyMissionDetail(program.getId(), program.getStartDate(), user.getId()),
                missionHelper.getMissionDashboardList(program.getId(), user.getId()),
                program.getEndDate().isBefore(LocalDateTime.now())
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

        return programHelper.getLetsChatAfterSessionNotice(program.getTitle(), program.getId());
    }

    /* [렛츠챗/챌린지] Zoom Meeting 생성 */
    private ZoomMeetingCreateResponse createZoomMeetingForLetsChatAndChallengeType(ProgramCreateRequestDTO programCreateRequestDTO) {
        ZoomMeetingCreateResponse zoomMeetingCreateResponse = null;
        if (programCreateRequestDTO.getType().equals(ProgramType.LETS_CHAT)
                || programCreateRequestDTO.getType().equals(ProgramType.CHALLENGE_HALF)
                || programCreateRequestDTO.getType().equals(ProgramType.CHALLENGE_FULL)) {
            zoomMeetingCreateResponse = zoomMeetingApiHelper.createMeeting(
                    programCreateRequestDTO.getType(),
                    programCreateRequestDTO.getTitle(),
                    programCreateRequestDTO.getTh(),
                    programCreateRequestDTO.getStartDate());
        }
        return zoomMeetingCreateResponse;
    }

    /* [렛츠챗] 멘토 세션 안내 페이지용 비밀번호 생성 */
    private String createMentorPasswordForLetsChatType(ProgramCreateRequestDTO programCreateRequestDTO) {
        String mentorPassword = null;
        if (programCreateRequestDTO.getType().equals(ProgramType.LETS_CHAT)) {
            mentorPassword = programHelper.generateRandomNumber();
        }
        return mentorPassword;
    }

    /* 이용료 프로그램 정보 입력 확인 */
    private void checkChargeFeeTypeForInput(ProgramCreateRequestDTO programCreateRequestDTO) {
        if (ProgramFeeType.CHARGE.equals(programCreateRequestDTO.getFeeType())) {
            programHelper.validateChargeTypeProgramInput(programCreateRequestDTO);
        }
    }

    /* 보증금 프로그램 정보 입력 확인 */
    private void checkRefundFeeTypeForInput(ProgramCreateRequestDTO programCreateRequestDTO) {
        if (ProgramFeeType.REFUND.equals(programCreateRequestDTO.getFeeType())) {
            programHelper.validateRefundTypeProgramInput(programCreateRequestDTO);
        }
    }

    /* 챌린지 프로그램 정보 입력 확인 */
    private void checkChallengeProgramTypeForInput(ProgramCreateRequestDTO programCreateRequestDTO) {
        ProgramType inputProgramType = programCreateRequestDTO.getType();
        if (ProgramType.CHALLENGE_HALF.equals(inputProgramType) || ProgramType.CHALLENGE_FULL.equals(inputProgramType)) {
            programHelper.validateChallengeTypeProgramInput(programCreateRequestDTO);
        }
    }

    /* program domain 생성 및 저장 */
    private Program createProgramAndSave(ProgramCreateRequestDTO programCreateRequestDTO,
                                         ZoomMeetingCreateResponse zoomMeetingCreateResponse,
                                         String mentorPassword) {
        Program newProgram = programMapper.toEntity(programCreateRequestDTO, mentorPassword, zoomMeetingCreateResponse);
        return programHelper.saveProgram(newProgram);
    }
}
