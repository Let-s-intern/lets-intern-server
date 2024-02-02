package com.letsintern.letsintern.domain.mission.repository;

import com.letsintern.letsintern.domain.mission.vo.MissionAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MissionRepositoryCustom {

    Page<MissionAdminVo> getMissionAdminList(Long programId, Pageable pageable);
}
