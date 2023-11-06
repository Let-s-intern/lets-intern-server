package com.letsintern.letsintern.domain.application.helper;

import com.letsintern.letsintern.domain.application.domain.GuestApplication;
import com.letsintern.letsintern.domain.application.domain.UserApplication;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.GuestApplicationCreateDTO;
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

    public Long createUserApplication(Long programId, ApplicationCreateDTO applicationCreateDTO, User user) {
        UserApplication newUserApplication = applicationMapper.toUserEntity(programId, applicationCreateDTO, user);
        return applicationRepository.save(newUserApplication).getId();
    }

    public Long createGuestApplication(Long programId, GuestApplicationCreateDTO guestApplicationCreateDTO) {
        GuestApplication newGuestApplication = applicationMapper.toGuestEntity(programId, guestApplicationCreateDTO);
        return applicationRepository.save(newGuestApplication).getId();
    }

    public List<UserApplication> getApplicationListOfProgramId(Long programId, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByProgramId(programId, pageRequest);
    }

    public List<UserApplication> getApplicationListOfProgramIdAndApproved(Long programId, Boolean approved, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByProgramIdAndApproved(programId, approved, pageRequest);
    }

    public List<UserApplication> getApplicationListOfUserId(Long userId, Pageable pageable) {
        PageRequest pageRequest = makePageRequest(pageable);
        return applicationRepository.findAllByUserId(userId, pageRequest);
    }

    private PageRequest makePageRequest(Pageable pageable) {
        int pageNum = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return PageRequest.of(pageNum, pageSize, Sort.by("id").descending());
    }
}
