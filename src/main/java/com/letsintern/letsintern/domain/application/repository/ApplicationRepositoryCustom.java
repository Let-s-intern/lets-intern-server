package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationVo;
import com.letsintern.letsintern.domain.program.domain.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationRepositoryCustom {

    Page<ApplicationAdminVo> findAllByProgramId(Long programId, Pageable pageable);

    Page<ApplicationAdminVo> findAllByProgramIdAndIsApproved(Long programId, Boolean isApproved, Pageable pageable);

    Page<ApplicationVo> findAllByUserId(Long userId, Pageable pageable);

    Page<Application> findAllByUserIdAdmin(Long userId, Pageable pageable);

    List<Program> findAllProgramByUserId(Long userId);

    Application findByProgramIdAndUserId(Long programId, Long userId);
    Application findByProgramIdAndGuestEmail(Long programId, String email);

    void updateAllStatusByProgramId(Long programId);

    List<String> findAllEmailByIsApproved(Long programId, Boolean isApproved);
}
