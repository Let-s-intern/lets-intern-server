package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationRepositoryCustom {

    List<Application> findAllByProgramId(Long programId, Pageable pageable);

    List<Application> findAllByProgramIdAndApproved(Long programId, Boolean approved, Pageable pageable);

    List<Application> findAllByUserId(Long userId, Pageable pageable);

}
