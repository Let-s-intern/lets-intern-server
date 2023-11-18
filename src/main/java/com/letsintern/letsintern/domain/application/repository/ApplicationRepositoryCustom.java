package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.GuestApplication;
import com.letsintern.letsintern.domain.application.domain.UserApplication;
import com.letsintern.letsintern.domain.application.vo.UserApplicationVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationRepositoryCustom {

    List<Application> findAllByProgramId(Long programId, Pageable pageable);

    List<Application> findAllByProgramIdAndIsApproved(Long programId, Boolean isApproved, Pageable pageable);

    List<UserApplicationVo> findAllByUserId(Long userId, Pageable pageable);

    UserApplication findByProgramIdAndUserId(Long programId, Long userId);
    GuestApplication findByProgramIdAndGuestEmail(Long programId, String email);

}
