package com.letsintern.letsintern.domain.application.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationHelper {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public Long createApplication(ApplicationCreateDTO applicationCreateDTO) {
        Application newApplication = applicationMapper.toEntity(applicationCreateDTO);
        Application savedApplication = applicationRepository.save(newApplication);

        return savedApplication.getId();
    }

    public List<Application> getApplicationListOfProgramId(Long programId) {
        return applicationRepository.findAllByProgramId(programId);
    }

    public List<Application> getApplicationListOfUserId(Long userId) {
        return applicationRepository.findAllByUserId(userId);
    }

}
