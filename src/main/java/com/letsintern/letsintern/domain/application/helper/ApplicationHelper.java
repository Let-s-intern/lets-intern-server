package com.letsintern.letsintern.domain.application.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationChallengeUpdateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationUpdateDTO;
import com.letsintern.letsintern.domain.application.exception.*;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationChallengeAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationEntireDashboardVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationVo;
import com.letsintern.letsintern.domain.payment.domain.FeeType;
import com.letsintern.letsintern.domain.program.domain.MailType;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.vo.ProgramEmailVo;
import com.letsintern.letsintern.domain.program.vo.program.ProgramDetailVo;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.domain.UserRole;
import com.letsintern.letsintern.global.common.util.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ApplicationHelper {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final EmailUtils emailUtils;

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

    /* 프로그램 1개의 전체 지원서 목록 */
    public Page<ApplicationAdminVo> getApplicationListOfProgramId(Long programId, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByProgramId(programId, pageRequest);
    }

    /* 프로그램 1개의 승인된 지원서 목록 */
    public Page<ApplicationAdminVo> getApplicationListOfProgramIdAndApproved(Long programId, Boolean isApproved, Pageable pageable) {
        return applicationRepository.findAllByProgramIdAndIsApproved(programId, isApproved, pageable);
    }

    /* 프로그램 1개의 안내 메일 전송 대상자 메일 주소 목록 */
    public List<String> getApplicationEmailListOfProgramIdAndMailType(Program program, MailType mailType) {
        switch (mailType) {
            case APPROVED -> {
                switch (program.getProgramType()) {
                    case CHALLENGE_FULL, CHALLENGE_HALF -> {
                        return applicationRepository.findAllEmailByIsApproved(program.getId(), true);
                    }
                    default -> {
                        return new ArrayList<>();
                    }
                }
            }
            case FEE_CONFIRMED -> {
                return applicationRepository.findAllEmailByIsApprovedAndFeeIsConfirmed(program.getId(), true, true);
            }
            default -> {
                return new ArrayList<>();
            }
        }
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

        if (applicationUpdateDTO.getFeeIsConfirmed() != null)
            application.setFeeIsConfirmed(applicationUpdateDTO.getFeeIsConfirmed());

        if (applicationUpdateDTO.getIsApproved() != null) {
            application.setIsApproved(applicationUpdateDTO.getIsApproved());

            if (application.getStatus().equals(ApplicationStatus.APPLIED) && application.getIsApproved().equals(true)) {
                application.setStatus(ApplicationStatus.IN_PROGRESS);

                // 렛츠챗 참여 확정 메일 전송
                if (application.getProgram().getProgramType().equals(ProgramType.LETS_CHAT) && application.getIsApproved() && application.getFeeIsConfirmed()) {
                    String emailAddress = (application.getUser() == null) ? application.getEmail() : application.getUser().getEmail();
                    emailUtils.sendApplicationApprovedEmail(emailAddress, ProgramEmailVo.from(application.getProgram()));
                }
            }

            if (application.getStatus().equals(ApplicationStatus.APPLIED) && application.getIsApproved().equals(false)) {
                application.setStatus(ApplicationStatus.APPLIED_NOT_APPROVED);
            }
        }

        if (applicationUpdateDTO.getStatus() != null)
            application.setStatus(applicationUpdateDTO.getStatus());
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

        if (application.getProgram().getStatus().equals(ProgramStatus.OPEN)) {
            application.getProgram().decreaseProgramApplicationCount();
            applicationRepository.delete(application);
        } else {
            throw ApplicationCannotDeleted.EXCEPTION;
        }
    }

    /* 챌린지 모두의 기록장 - 한 줄 소개, 직무 수정 */
    public Long updateChallengeApplication(Long applicationId, ApplicationChallengeUpdateDTO applicationChallengeUpdateDTO, Long userId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> ApplicationNotFound.EXCEPTION);
        if (!application.getUser().getId().equals(userId)) throw ApplicationUnauthorized.EXCEPTION;

        if (applicationChallengeUpdateDTO.getIntroduction() != null)
            application.setIntroduction(applicationChallengeUpdateDTO.getIntroduction());

        if (applicationChallengeUpdateDTO.getWishJob() != null)
            application.setWishJob(applicationChallengeUpdateDTO.getWishJob());

        return application.getId();
    }

    private PageRequest makePageRequest(Pageable pageable) {
        int pageNum = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
    }

    public Page<ApplicationEntireDashboardVo> getDashboardList(Long programId, ApplicationWishJob applicationWishJob, Long userId, Pageable pageable) {
        return applicationRepository.getEntireDashboardList(programId, applicationWishJob, userId, pageable);
    }

    public Page<ApplicationChallengeAdminVo> getApplicationChallengeAdminList(Long programId, Pageable pageable, String name, String email, String phoneNum) {
        if (name != null || email != null || phoneNum != null) {
            return applicationRepository.getApplicationChallengeAdminListFiltered(programId, pageable, name, email, phoneNum);
        }
        return applicationRepository.getApplicationChallengeAdminList(programId, pageable);
    }

    public Integer calculateTotalFee(ProgramDetailVo programDetailVo, Integer couponValue) {
        return (programDetailVo.feeCharge() + programDetailVo.feeCharge()) - programDetailVo.discountValue() - couponValue;
    }

    public void validateGuestApplicationInput(ProgramDetailVo programDetailVo, ApplicationCreateDTO applicationCreateDTO) {
        validateGuestInfo(applicationCreateDTO);
        validateDuplicateGuestApplication(programDetailVo.programId(), applicationCreateDTO);
        validateRefundTypeInput(programDetailVo, applicationCreateDTO);
    }

    public void validateDuplicateApplication(Long programId, User user) {
        if (checkUserApplicationExist(programId, user.getId()))
            throw DuplicateApplication.EXCEPTION;
    }

    public void validateHasUserDetailInfo(ApplicationCreateDTO applicationCreateDTO) {
        if (Objects.isNull(applicationCreateDTO.getUniversity()) || Objects.isNull(applicationCreateDTO.getMajor()))
            throw ApplicationUserBadRequest.EXCEPTION;
    }

    public void validateHasUserAccountInfo(ApplicationCreateDTO applicationCreateDTO) {
        if (Objects.isNull(applicationCreateDTO.getAccountType()) || Objects.isNull(applicationCreateDTO.getAccountNumber()))
            throw ApplicationUserBadRequestAccount.EXCEPTION;
    }

    public void validateIsChallengeParticipant(UserRole userRole, Long programId, Long userId) {
        if (!userRole.equals(UserRole.ROLE_ADMIN)) {
            final Application application = applicationRepository.findByProgramIdAndUserId(programId, userId);
            if (application == null) throw ApplicationNotFound.EXCEPTION;
        }
    }

    /* 비회원 신청인 경우 name, phoneNum, email 입력 여부 확인 */
    private void validateGuestInfo(ApplicationCreateDTO applicationCreateDTO) {
        if (Objects.isNull(applicationCreateDTO.getGuestName())
                || Objects.isNull(applicationCreateDTO.getGuestPhoneNum())
                || Objects.isNull(applicationCreateDTO.getGuestEmail())) {
            throw ApplicationGuestBadRequest.EXCEPTION;
        }
    }

    /* 기존 신청 내역 확인 */
    private void validateDuplicateGuestApplication(Long programId, ApplicationCreateDTO applicationCreateDTO) {
        if (checkGuestApplicationExist(programId, applicationCreateDTO.getGuestEmail()))
            throw DuplicateApplication.EXCEPTION;
    }

    /* 보증금 프로그램인데 계좌 추가 정보 없는 경우 */
    private void validateRefundTypeInput(ProgramDetailVo programDetailVo, ApplicationCreateDTO applicationCreateDTO) {
        if (!programDetailVo.feeType().equals(FeeType.REFUND)) return;
        if (Objects.isNull(applicationCreateDTO.getAccountType()) || Objects.isNull(applicationCreateDTO.getAccountNumber()))
            throw ApplicationUserBadRequestAccount.EXCEPTION;
    }
}
