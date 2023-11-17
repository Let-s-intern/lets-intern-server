package com.letsintern.letsintern.domain.application.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.GuestApplication;
import com.letsintern.letsintern.domain.application.domain.UserApplication;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationCreateResponse;
import com.letsintern.letsintern.domain.application.exception.ApplicationGuestBadRequest;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.exception.ApplicationUserBadRequest;
import com.letsintern.letsintern.domain.application.exception.DuplicateApplication;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
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

    public void checkUserApplicationHistory(Long programId, User user) {
        /* 기존 신청 내역 확인 */
        UserApplication userApplication = applicationRepository.findByProgramIdAndUserId(programId, user.getId());
        if(userApplication != null) throw DuplicateApplication.EXCEPTION;

        /* 사용자 상세 정보 존재 여부 확인 */
        if(user.getUniversity() == null || user.getMajor() == null) throw ApplicationUserBadRequest.EXCEPTION;
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

    public List<Application> getApplicationListOfProgramIdAndApproved(Long programId, Boolean approved, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByProgramIdAndApproved(programId, approved, pageRequest);
    }

    public List<UserApplication> getApplicationListOfUserId(Long userId, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByUserId(userId, pageRequest);
    }

    public Long updateApplicationApproved(Long applicationId, Boolean approved) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> {
                    throw ApplicationNotFound.EXCEPTION;
                });

        application.setApproved(approved);
        return application.getId();
    }

    private PageRequest makePageRequest(Pageable pageable) {
        int pageNum = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
    }

}
