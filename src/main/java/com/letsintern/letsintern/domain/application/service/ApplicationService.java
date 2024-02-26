package com.letsintern.letsintern.domain.application.service;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationChallengeUpdateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationUpdateDTO;
import com.letsintern.letsintern.domain.application.dto.response.*;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.exception.ApplicationUserBadRequest;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.mission.repository.MissionRepository;
import com.letsintern.letsintern.domain.program.domain.Program;
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
    private final MissionRepository missionRepository;

    @Transactional
    public ApplicationCreateResponse createUserApplication(Long programId, ApplicationCreateDTO applicationCreateDTO, PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();

        /* 대학 & 전공 추가 정보 없는 사용자 */
        if(!userService.checkDetailInfoExist(principalDetails)) {
            if(applicationCreateDTO.getUniversity() == null || applicationCreateDTO.getMajor() == null) {
                throw ApplicationUserBadRequest.EXCEPTION;  // 추가 정보 미입력
            }
            else userService.addUserDetailInfo(user, applicationCreateDTO.getUniversity(), applicationCreateDTO.getMajor());
        }

        return applicationHelper.createUserApplication(programId, applicationCreateDTO, user);
    }

    @Transactional
    public ApplicationCreateResponse createGuestApplication(Long programId, ApplicationCreateDTO applicationCreateDTO) {
        return applicationHelper.createGuestApplication(programId, applicationCreateDTO);
    }

    public AdminApplicationListResponse getApplicationListOfProgram(Long programId, Pageable pageable) {
        return applicationMapper.toAdminApplicationListResponse(applicationHelper.getApplicationListOfProgramId(programId, pageable));
    }

    public AdminApplicationListResponse getApplicationListOfProgramAndApproved(Long programId, Boolean approved, Pageable pageable) {
        return applicationMapper.toAdminApplicationListResponse(
                applicationHelper.getApplicationListOfProgramIdAndApproved(programId, approved, pageable)
        );
    }

    public UserApplicationListResponse getApplicationListOfUser(Long userId, Pageable pageable) {
        return applicationMapper.toUserApplicationListResponse(applicationHelper.getApplicationListOfUserId(userId, pageable));
    }

    public ApplicationListResponse getAdminApplicationListOfUserId(Long userId, Pageable pageable) {
        return applicationMapper.toApplicationListResponse(applicationHelper.getAdminApplicationListOfUserId(userId, pageable));
    }

    @Transactional
    public ApplicationIdResponse updateApplication(Long applicationId, ApplicationUpdateDTO applicationUpdateDTO) {
        return applicationMapper.toApplicationIdResponse(applicationHelper.updateApplication(applicationId, applicationUpdateDTO));
    }


    @Transactional
    public void deleteApplication(Long applicationId) {
        applicationHelper.deleteApplication(applicationId);
    }


    public ApplicationValidityResponse checkApplicationValidity(Long programId, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return ApplicationValidityResponse.from(applicationRepository.findByProgramIdAndUserIdAndIsApproved(programId, user.getId(), true).orElse(null) != null);
    }

    @Transactional
    public ApplicationIdResponse updateChallengeApplication(Long applicationId, ApplicationChallengeUpdateDTO applicationChallengeUpdateDTO, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return applicationMapper.toApplicationIdResponse(applicationHelper.updateChallengeApplication(applicationId, applicationChallengeUpdateDTO, user.getId()));
    }

    public EmailListResponse getEmailList(Long programId) {
        return applicationMapper.toEmailListResponse(
                applicationRepository.findAllEmailByIsApproved(programId, true),
                applicationRepository.findAllEmailByIsApproved(programId, false)
        );
    }

    public ApplicationChallengeAdminVosResponse getApplicationChallengeAdminList(Long programId, Pageable pageable, String name, String email, String phoneNum) {
        return applicationMapper.toApplicationChallengeAdminVosResponse(applicationHelper.getApplicationChallengeAdminList(programId, pageable, name, email, phoneNum));
    }

    public ApplicationChallengeAdminVoDetail getApplicationChallengeAdminDetail(Long applicationId) {
        final Application application = applicationRepository.findById(applicationId).orElseThrow(() -> ApplicationNotFound.EXCEPTION);
        return ApplicationChallengeAdminVoDetail.of(application.getApplyMotive(), missionRepository.getMissionAdminApplicationVos(application.getProgram().getId(), application.getUser().getId()));
    }
}
