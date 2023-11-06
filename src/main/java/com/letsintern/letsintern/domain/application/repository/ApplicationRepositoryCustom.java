package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.UserApplication;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationRepositoryCustom {

    List<Application> findAllByProgramId(Long programId, Pageable pageable);

    List<Application> findAllByProgramIdAndApproved(Long programId, Boolean approved, Pageable pageable);

    List<UserApplication> findAllByUserId(Long userId, Pageable pageable);

}
