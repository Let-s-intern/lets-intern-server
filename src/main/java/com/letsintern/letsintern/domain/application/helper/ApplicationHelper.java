package com.letsintern.letsintern.domain.application.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationUpdateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationCreateResponse;
import com.letsintern.letsintern.domain.application.exception.*;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationVo;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationHelper {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final ProgramRepository programRepository;


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
        Application newUserApplication = applicationMapper.toEntity(programId, applicationCreateDTO, user);

        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });
        program.setApplicationCount(program.getApplicationCount() + 1);
        return applicationMapper.toApplicationCreateResponse(applicationRepository.save(newUserApplication));
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
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });
        program.setApplicationCount(program.getApplicationCount() + 1);
        return applicationMapper.toApplicationCreateResponse(applicationRepository.save(newGuestApplication));
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

        if(application.getProgram().getStatus().equals(ProgramStatus.OPEN)) {
            application.getProgram().setApplicationCount(application.getProgram().getApplicationCount() - 1);
            applicationRepository.delete(application);
        } else {
            throw ApplicationCannotDeleted.EXCEPTION;
        }

    }

    private PageRequest makePageRequest(Pageable pageable) {
        int pageNum = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
    }
}
