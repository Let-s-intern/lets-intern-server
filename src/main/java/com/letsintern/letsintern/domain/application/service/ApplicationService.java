package com.letsintern.letsintern.domain.application.service;

import com.letsintern.letsintern.domain.application.domain.ApplicationStatus;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationUpdateDTO;
import com.letsintern.letsintern.domain.application.dto.response.*;
import com.letsintern.letsintern.domain.application.exception.ApplicationUserBadRequest;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.service.UserService;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationHelper applicationHelper;
    private final ApplicationMapper applicationMapper;
    private final UserService userService;

    public ApplicationCreateResponse createUserApplication(Long programId, ApplicationCreateDTO applicationCreateDTO, PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();

        /* 추가 정보 없는 사용자 */
        if(!userService.checkDetailInfoExist(principalDetails)) {
            if(applicationCreateDTO.getUniversity() == null || applicationCreateDTO.getMajor() == null) {
                throw ApplicationUserBadRequest.EXCEPTION;  // 추가 정보 미입력
            }
            else userService.addUserDetailInfo(user, applicationCreateDTO.getUniversity(), applicationCreateDTO.getMajor());
        }

        return applicationHelper.createUserApplication(programId, applicationCreateDTO, user);
    }

    public ApplicationCreateResponse createGuestApplication(Long programId, ApplicationCreateDTO applicationCreateDTO) {
        return applicationHelper.createGuestApplication(programId, applicationCreateDTO);
    }

    public ApplicationListResponse getApplicationListOfProgram(Long programId, Pageable pageable) {
        return applicationMapper.toApplicationListResponseDTO(applicationHelper.getApplicationListOfProgramId(programId, pageable));
    }

    public ApplicationListResponse getApplicationListOfProgramAndApproved(Long programId, Boolean approved, Pageable pageable) {
        return applicationMapper.toApplicationListResponseDTO(
                applicationHelper.getApplicationListOfProgramIdAndApproved(programId, approved, pageable)
        );
    }

    public UserApplicationListResponse getApplicationListOfUser(Long userId, Pageable pageable) {
        return applicationMapper.toUserApplicationListResponse(applicationHelper.getApplicationListOfUserId(userId, pageable));
    }

    @Transactional
    public ApplicationIdResponse updateApplication(Long applicationId, ApplicationUpdateDTO applicationUpdateDTO) {
        return applicationMapper.toApplicationIdResponse(applicationHelper.updateApplication(applicationId, applicationUpdateDTO));
    }


    @Transactional
    public void deleteApplication(Long applicationId) {
        applicationHelper.deleteApplication(applicationId);
    }

    @Transactional
    public String updateApplicationNotApproved(Long programId) {
        return applicationHelper.updateApplicationNotApproved(programId);
    }

    public EmailListResponse getEmailList(Long programId) {
        return applicationMapper.toEmailListResponse(
                applicationRepository.findAllEmailByStatus(programId, ApplicationStatus.IN_PROGRESS),
                applicationRepository.findAllEmailByStatus(programId, ApplicationStatus.APPLIED_NOT_APPROVED)
        );
    }
}
