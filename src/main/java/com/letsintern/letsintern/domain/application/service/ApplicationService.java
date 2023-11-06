package com.letsintern.letsintern.domain.application.service;

import com.letsintern.letsintern.domain.application.domain.GuestApplication;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.GuestApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationIdResponseDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationListResponseDTO;
import com.letsintern.letsintern.domain.application.dto.response.UserApplicationListResponseDTO;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationHelper applicationHelper;
    private final ApplicationMapper applicationMapper;

    public ApplicationIdResponseDTO createUserApplication(Long programId, ApplicationCreateDTO applicationCreateDTO, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return applicationMapper.toApplicationIdResponse(applicationHelper.createUserApplication(programId, applicationCreateDTO, user));
    }

    public ApplicationIdResponseDTO createGuestApplication(Long programId, GuestApplicationCreateDTO guestApplicationCreateDTO) {
        return applicationMapper.toApplicationIdResponse(applicationHelper.createGuestApplication(programId, guestApplicationCreateDTO));
    }

    public ApplicationListResponseDTO getApplicationListOfProgram(Long programId, Pageable pageable) {
        return applicationMapper.toApplicationListResponseDTO(applicationHelper.getApplicationListOfProgramId(programId, pageable));
    }

    public ApplicationListResponseDTO getApplicationListOfProgramAndApproved(Long programId, Boolean approved, Pageable pageable) {
        return applicationMapper.toApplicationListResponseDTO(
                applicationHelper.getApplicationListOfProgramIdAndApproved(programId, approved, pageable)
        );
    }

    public UserApplicationListResponseDTO getApplicationListOfUser(Long userId, Pageable pageable) {
        return applicationMapper.toUserApplicationListResponseDTO(applicationHelper.getApplicationListOfUserId(userId, pageable));
    }

    public ApplicationIdResponseDTO updateApplicationApproved(Long applicationId, Boolean approved) {
        return applicationMapper.toApplicationIdResponse(applicationHelper.updateApplicationApproved(applicationId, approved));
    }
}
