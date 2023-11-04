package com.letsintern.letsintern.domain.application.service;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationIdResponseDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationListResponseDTO;
import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
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

    public ApplicationIdResponseDTO createApplication(ApplicationCreateDTO applicationCreateDTO) {
        return applicationMapper.toApplicationIdResponse(applicationHelper.createApplication(applicationCreateDTO));
    }

    public ApplicationListResponseDTO getApplicationListOfProgram(Long programId, Pageable pageable) {
        return applicationMapper.toApplicationListResponseDTO(applicationHelper.getApplicationListOfProgramId(programId, pageable));
    }

    public ApplicationListResponseDTO getApplicationListOfProgramAndApproved(Long programId, Boolean approved, Pageable pageable) {
        return applicationMapper.toApplicationListResponseDTO(
                applicationHelper.getApplicationListOfProgramIdAndApproved(programId, approved, pageable)
        );
    }

    public ApplicationListResponseDTO getApplicationListOfUser(Long userId, Pageable pageable) {
        return applicationMapper.toApplicationListResponseDTO(applicationHelper.getApplicationListOfUserId(userId, pageable));
    }
}
