package com.letsintern.letsintern.domain.application.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.mapper.ApplicationMapper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
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

    public Long createApplication(ApplicationCreateDTO applicationCreateDTO) {
        Application newApplication = applicationMapper.toEntity(applicationCreateDTO);
        Application savedApplication = applicationRepository.save(newApplication);

        return savedApplication.getId();
    }

    public List<Application> getApplicationListOfProgramId(Long programId, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByProgramId(programId, pageRequest);
    }

    public List<Application> getApplicationListOfProgramIdAndApproved(Long programId, Boolean approved, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByProgramIdAndApproved(programId, approved, pageRequest);
    }

    public List<Application> getApplicationListOfUserId(Long userId, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByUserId(userId, pageRequest);
    }

    private PageRequest makePageRequest(Pageable pageable) {
        int pageNum = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
    }
}
