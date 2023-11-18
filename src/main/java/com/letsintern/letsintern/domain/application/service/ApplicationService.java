package com.letsintern.letsintern.domain.application.service;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationUpdateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationCreateResponse;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationIdResponse;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationListResponse;
import com.letsintern.letsintern.domain.application.dto.response.UserApplicationListResponse;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationHelper applicationHelper;
    private final ApplicationMapper applicationMapper;

    public void checkUserApplicationHistory(Long programId, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        applicationHelper.checkUserApplicationHistory(programId, user);
    }

    public ApplicationCreateResponse createUserApplication(Long programId, ApplicationCreateDTO applicationCreateDTO, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
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


}
