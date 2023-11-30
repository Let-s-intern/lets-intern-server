package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationVo;
import com.letsintern.letsintern.domain.program.domain.Program;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationRepositoryCustom {

    List<ApplicationAdminVo> findAllByProgramId(Long programId, Pageable pageable);

    List<ApplicationAdminVo> findAllByProgramIdAndIsApproved(Long programId, Boolean isApproved, Pageable pageable);

    List<ApplicationVo> findAllByUserId(Long userId, Pageable pageable);

    List<Application> findAllByUserId(Long userId);

    List<Program> findAllProgramByUserId(Long userId);

    Application findByProgramIdAndUserId(Long programId, Long userId);
    Application findByProgramIdAndGuestEmail(Long programId, String email);

    void updateAllStatusByProgramId(Long programId);

    List<String> findAllEmailByIsApproved(Long programId, Boolean isApproved);
}
