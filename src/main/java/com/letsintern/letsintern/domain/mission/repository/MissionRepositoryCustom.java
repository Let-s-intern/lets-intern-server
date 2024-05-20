package com.letsintern.letsintern.domain.mission.repository;

import com.letsintern.letsintern.domain.mission.vo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MissionRepositoryCustom {

    Page<MissionAdminVo> getMissionAdminList(Long programId, Pageable pageable);

    Optional<MissionAdminDetailVo> getMissionAdminDetailVo(Long missionId);

    List<MissionAdminSimpleVo> getMissionAdminSimpleList(Long programId);

    List<MissionDashboardListVo> getMissionDashboardList(Long programId, Long userId);

    List<MissionMyDashboardListVo> getMissionMyDashboardList(Long programId, Long userId);

    Optional<MissionMyDashboardDoneVo> getMissionMyDashboardDoneVo(Long missionId, Long userId);

    Optional<MissionMyDashboardYetVo> getMissionMyDashboardYetVo(Long missionId);

    Optional<MissionMyDashboardAbsentVo> getMissionMyDashboardAbsentVo(Long missionId, Long userId);

    List<MissionAdminApplicationVo> getMissionAdminApplicationVos(Long programId, Long userId);

    Optional<MissionDashboardVo> getDailyMission(Long programId);

    Optional<MissionMyDashboardVo> getDailyMissionDetail(Long programId, Long userId);
}
