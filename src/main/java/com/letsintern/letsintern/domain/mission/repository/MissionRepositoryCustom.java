package com.letsintern.letsintern.domain.mission.repository;

import com.letsintern.letsintern.domain.mission.vo.MissionAdminVo;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardListVo;
import com.letsintern.letsintern.domain.mission.vo.MissionDashboardVo;
import com.letsintern.letsintern.domain.mission.vo.MissionMyDashboardVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MissionRepositoryCustom {

    Page<MissionAdminVo> getMissionAdminList(Long programId, Pageable pageable);

    Optional<MissionDashboardVo> getMissionDashboardVo(Long programId, Integer th);

    Optional<MissionMyDashboardVo> getMissionMyDashboardVo(Long programId, Integer th, Long userId);

    List<MissionDashboardListVo> getMissionDashboardList(Long programId, Long userId);
}
