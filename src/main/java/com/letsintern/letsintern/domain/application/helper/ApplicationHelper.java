package com.letsintern.letsintern.domain.application.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.application.domain.GuestApplication;
import com.letsintern.letsintern.domain.application.domain.UserApplication;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationUpdateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationCreateResponse;
import com.letsintern.letsintern.domain.application.exception.*;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.application.vo.UserApplicationVo;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
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


    /* 회원 - 기존 신청 내역 확인 */
    public boolean checkUserApplicationExist(Long programId, Long userId) {
        UserApplication userApplication = applicationRepository.findByProgramIdAndUserId(programId, userId);
        if(userApplication != null) return true;
        return false;
    }

    public ApplicationCreateResponse createUserApplication(Long programId, ApplicationCreateDTO applicationCreateDTO, User user) {
        UserApplication newUserApplication = applicationMapper.toUserEntity(programId, applicationCreateDTO, user);
        return applicationMapper.toApplicationCreateResponse(applicationRepository.save(newUserApplication));
    }

    public ApplicationCreateResponse createGuestApplication(Long programId, ApplicationCreateDTO applicationCreateDTO) {
        /* 비회원 신청인 경우 name, phoneNum, email 입력 여부 확인 */
        if(applicationCreateDTO.getGuestName() == null || applicationCreateDTO.getGuestPhoneNum() == null || applicationCreateDTO.getGuestEmail() == null) {
            throw ApplicationGuestBadRequest.EXCEPTION;
        }

        /* 기존 신청 내역 확인 */
        GuestApplication guestApplication = applicationRepository.findByProgramIdAndGuestEmail(programId, applicationCreateDTO.getGuestEmail());
        if(guestApplication != null) throw DuplicateApplication.EXCEPTION;

        GuestApplication newGuestApplication = applicationMapper.toGuestEntity(programId, applicationCreateDTO);
        return applicationMapper.toApplicationCreateResponse(applicationRepository.save(newGuestApplication));
    }

    public List<Application> getApplicationListOfProgramId(Long programId, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByProgramId(programId, pageRequest);
    }

    public List<Application> getApplicationListOfProgramIdAndApproved(Long programId, Boolean isApproved, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByProgramIdAndIsApproved(programId, isApproved, pageRequest);
    }

    public List<UserApplicationVo> getApplicationListOfUserId(Long userId, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByUserId(userId, pageRequest);
    }

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

    private PageRequest makePageRequest(Pageable pageable) {
        int pageNum = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
    }

    public void deleteApplication(Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> {
                    throw ApplicationNotFound.EXCEPTION;
                });

        if(application.getProgram().getStatus().equals(ProgramStatus.OPEN)) {
            applicationRepository.delete(application);
        } else {
            throw ApplicationCannotDeleted.EXCEPTION;
        }

    }

    public String updateApplicationNotApproved(Long programId) {
        applicationRepository.updateAllStatusByProgramId(programId);
        return "success";
    }
}
