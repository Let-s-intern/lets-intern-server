package com.letsintern.letsintern.domain.application.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationChallengeUpdateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationUpdateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationCreateResponse;
import com.letsintern.letsintern.domain.application.exception.*;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationChallengeAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationEntireDashboardVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationVo;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramFeeType;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.vo.ProgramEmailVo;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.service.UserService;
import com.letsintern.letsintern.global.common.util.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationHelper {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final ProgramRepository programRepository;
    private final EmailUtils emailUtils;
    private final UserService userService;


    /* 회원 - 기존 신청 내역 확인 */
    public boolean checkUserApplicationExist(Long programId, Long userId) {
        Application application = applicationRepository.findByProgramIdAndUserId(programId, userId);
        return application != null;
    }

    /* 비회원 - 기존 신청 내역 확인 */
    private boolean checkGuestApplicationExist(Long programId, String guestEmail) {
        Application application = applicationRepository.findByProgramIdAndGuestEmail(programId, guestEmail);
        return application != null;
    }

    /* 회원 - 지원서 생성 */
    public ApplicationCreateResponse createUserApplication(Long programId, ApplicationCreateDTO applicationCreateDTO, User user) {
        if(checkUserApplicationExist(programId, user.getId())) throw DuplicateApplication.EXCEPTION;
        Program program = programRepository.findById(programId).orElseThrow(() -> ProgramNotFound.EXCEPTION);

        /* 보증금 프로그램인데 계좌 추가 정보 없는 사용자 */
        if(program.getFeeType().equals(ProgramFeeType.REFUND) && !userService.checkDetailAccountInfoExist(user)) {
            if(applicationCreateDTO.getAccountType() == null || applicationCreateDTO.getAccountNumber() == null) {
                throw ApplicationUserBadRequestAccount.EXCEPTION;
            } else
                userService.addUserDetailAccountInfo(user, applicationCreateDTO.getAccountType(), applicationCreateDTO.getAccountNumber());
        }

        Application newUserApplication = applicationMapper.toEntity(programId, applicationCreateDTO, user);
        Application savedApplication = applicationRepository.save(newUserApplication);

        program.setApplicationCount(program.getApplicationCount() + 1);

        if(program.getType().equals(ProgramType.LETS_CHAT)) {
            emailUtils.sendApplicationApprovedEmail(user.getEmail(), user.getName(), ProgramEmailVo.from(program));
        }

        return applicationMapper.toApplicationCreateResponse(savedApplication);
    }

    /* 비회원 - 지원서 생성 */
    public ApplicationCreateResponse createGuestApplication(Long programId, ApplicationCreateDTO applicationCreateDTO) {
        /* 비회원 신청인 경우 name, phoneNum, email 입력 여부 확인 */
        if(applicationCreateDTO.getGuestName() == null || applicationCreateDTO.getGuestPhoneNum() == null || applicationCreateDTO.getGuestEmail() == null) {
            throw ApplicationGuestBadRequest.EXCEPTION;
        }

        /* 기존 신청 내역 확인 */
        if(checkGuestApplicationExist(programId, applicationCreateDTO.getGuestEmail())) throw DuplicateApplication.EXCEPTION;

        Application newGuestApplication = applicationMapper.toEntity(programId, applicationCreateDTO, null);
        Application savedApplication = applicationRepository.save(newGuestApplication);

        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });
        program.setApplicationCount(program.getApplicationCount() + 1);

        if(program.getType().equals(ProgramType.LETS_CHAT)) {
            emailUtils.sendApplicationApprovedEmail(applicationCreateDTO.getGuestEmail(), applicationCreateDTO.getGuestName(), ProgramEmailVo.from(program));
        }

        return applicationMapper.toApplicationCreateResponse(savedApplication);
    }

    /* 프로그램 1개의 전체 지원서 목록 */
    public Page<ApplicationAdminVo> getApplicationListOfProgramId(Long programId, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByProgramId(programId, pageRequest);
    }

    /* 프로그램 1개의 승인된 지원서 목록 */
    public Page<ApplicationAdminVo> getApplicationListOfProgramIdAndApproved(Long programId, Boolean isApproved, Pageable pageable) {
        return applicationRepository.findAllByProgramIdAndIsApproved(programId, isApproved, pageable);
    }

    /* 마이페이지 - 사용자 1명의 지원서 목록 */
    public Page<ApplicationVo> getApplicationListOfUserId(Long userId, Pageable pageable) {
        return applicationRepository.findAllByUserId(userId, pageable);
    }

    /* 어드민 - 사용자 1명의 지원서 목록 */
    public Page<Application> getAdminApplicationListOfUserId(Long userId, Pageable pageable) {
        return applicationRepository.findAllByUserIdAdmin(userId, pageable);
    }

    /* 지원서 1개 업데이트 */
    public Long updateApplication(Long applicationId, ApplicationUpdateDTO applicationUpdateDTO) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> {
                    throw ApplicationNotFound.EXCEPTION;
                });

        if (applicationUpdateDTO.getIsApproved() != null) {
            application.setIsApproved(applicationUpdateDTO.getIsApproved());

            if (application.getStatus().equals(ApplicationStatus.APPLIED) && application.getIsApproved().equals(true)) {
                application.setStatus(ApplicationStatus.IN_PROGRESS);
            }

            if (application.getStatus().equals(ApplicationStatus.APPLIED) && application.getIsApproved().equals(false)) {
                application.setStatus(ApplicationStatus.APPLIED_NOT_APPROVED);
            }
            // 이용료/보증금 입금 안내 이메일 전송
        }

        if (applicationUpdateDTO.getStatus() != null)
            application.setStatus(applicationUpdateDTO.getStatus());
        if(applicationUpdateDTO.getFeeIsConfirmed() != null) {
            application.setFeeIsConfirmed(applicationUpdateDTO.getFeeIsConfirmed());
            // 참여 확정 이메일 전송
        }
        if (applicationUpdateDTO.getGrade() != null)
            application.setGrade(applicationUpdateDTO.getGrade());
        if (applicationUpdateDTO.getWishCompany() != null)
            application.setWishCompany(applicationUpdateDTO.getWishCompany());
        if (applicationUpdateDTO.getWishJob() != null)
            application.setWishJob(application.getWishJob());
        if (applicationUpdateDTO.getApplyMotive() != null)
            application.setApplyMotive(application.getApplyMotive());
        if (applicationUpdateDTO.getAttendance() != null)
            application.setAttendance(applicationUpdateDTO.getAttendance());

        return application.getId();

    }

    /* 지원서 1개 삭제 */
    public void deleteApplication(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> {
                    throw ApplicationNotFound.EXCEPTION;
                });

        if(application.getProgram().getStatus().equals(ProgramStatus.OPEN)) {
            application.getProgram().setApplicationCount(application.getProgram().getApplicationCount() - 1);
            applicationRepository.delete(application);
        } else {
            throw ApplicationCannotDeleted.EXCEPTION;
        }

    }

    /* 챌린지 모두의 기록장 - 한 줄 소개, 직무 수정 */
    public Long updateChallengeApplication(Long applicationId, ApplicationChallengeUpdateDTO applicationChallengeUpdateDTO, Long userId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> ApplicationNotFound.EXCEPTION);
        if(!application.getUser().getId().equals(userId)) throw ApplicationUnauthorized.EXCEPTION;

        if(applicationChallengeUpdateDTO.getIntroduction() != null)
            application.setIntroduction(applicationChallengeUpdateDTO.getIntroduction());

        if(applicationChallengeUpdateDTO.getWishJob() != null)
            application.setWishJob(applicationChallengeUpdateDTO.getWishJob());

        return application.getId();
    }

    private PageRequest makePageRequest(Pageable pageable) {
        int pageNum = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
    }

    public ApplicationEntireDashboardVo getMyDashboard(Long programId, Long userId) {
        return applicationRepository.getEntireDashboardMyVo(programId, userId).orElseThrow(() -> ApplicationNotFound.EXCEPTION);
    }

    public Page<ApplicationEntireDashboardVo> getDashboardList(Long programId, ApplicationWishJob applicationWishJob, Long userId, Pageable pageable) {
        return applicationRepository.getEntireDashboardList(programId, applicationWishJob, userId, pageable);
    }

    public Page<ApplicationChallengeAdminVo> getApplicationChallengeAdminList(Long programId, Pageable pageable) {
        return applicationRepository.getApplicationChallengeAdminList(programId, pageable);
    }
}
