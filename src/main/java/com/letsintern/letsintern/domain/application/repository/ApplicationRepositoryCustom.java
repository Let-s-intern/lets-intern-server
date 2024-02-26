package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationChallengeAdminVoDetail;
import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationChallengeAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationEntireDashboardVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationVo;
import com.letsintern.letsintern.domain.program.vo.UserProgramVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ApplicationRepositoryCustom {

    Page<ApplicationAdminVo> findAllByProgramId(Long programId, Pageable pageable);

    Page<ApplicationAdminVo> findAllByProgramIdAndIsApproved(Long programId, Boolean isApproved, Pageable pageable);

    Page<ApplicationVo> findAllByUserId(Long userId, Pageable pageable);

    Page<Application> findAllByUserIdAdmin(Long userId, Pageable pageable);

    Page<UserProgramVo> findAllProgramByUserId(Long userId, Pageable pageable);

    Application findByProgramIdAndUserId(Long programId, Long userId);

    Application findByProgramIdAndGuestEmail(Long programId, String email);

    void updateAllApplicationByAnnouncementDate(LocalDateTime now);

    void updateAllApplicationStatusDone(Long programId);

    List<String> findAllEmailByIsApproved(Long programId, Boolean isApproved);

    List<String> findAllEmailByIsApprovedAndFeeIsConfirmed(Long programId, Boolean isApproved, Boolean feeIsConfirmed);

    Page<ApplicationEntireDashboardVo> getEntireDashboardList(Long programId, ApplicationWishJob applicationWishJob, Long userId, Pageable pageable);

    Page<ApplicationChallengeAdminVo> getApplicationChallengeAdminList(Long programId, Pageable pageable);

}
