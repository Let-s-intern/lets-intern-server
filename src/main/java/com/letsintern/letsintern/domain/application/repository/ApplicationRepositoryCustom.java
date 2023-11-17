package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.GuestApplication;
import com.letsintern.letsintern.domain.application.domain.UserApplication;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepositoryCustom {

    List<Application> findAllByProgramId(Long programId, Pageable pageable);

    List<Application> findAllByProgramIdAndApproved(Long programId, Boolean approved, Pageable pageable);

    List<UserApplication> findAllByUserId(Long userId, Pageable pageable);

    UserApplication findByProgramIdAndUserId(Long programId, Long userId);
    GuestApplication findByProgramIdAndGuestEmail(Long programId, String email);

}
