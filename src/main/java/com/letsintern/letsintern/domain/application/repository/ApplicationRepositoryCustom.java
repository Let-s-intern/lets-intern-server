package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.Application;

import java.util.List;

public interface ApplicationRepositoryCustom {

    List<Application> findAllByProgramId(Long programId);

    List<Application> findAllByUserId(Long userId);

}
