package com.letsintern.letsintern.domain.mission.repository;

import com.letsintern.letsintern.domain.mission.domain.MissionDashboardListStatus;
import com.letsintern.letsintern.domain.mission.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MissionRepositoryCustom {

    Page<MissionAdminVo> getMissionAdminList(Long programId, Pageable pageable);

    Optional<MissionDashboardVo> getMissionDashboardVo(Long programId, Integer th);

    Optional<MissionMyDashboardVo> getMissionMyDashboardVo(Long programId, Integer th, Long userId);

    List<MissionDashboardListVo> getMissionDashboardList(Long programId, Long userId);

    List<MissionMyDashboardListVo> getMissionMyDashboardList(Long programId, MissionDashboardListStatus status, Long userId);

    Optional<MissionMyDashboardCompletedVo> getMissionMyDashboardCompleted(Long missionId, Long userId);

    Optional<MissionMyDashboardUncompletedVo> getMissionMyDashboardUncompleted(Long missionId);
}
