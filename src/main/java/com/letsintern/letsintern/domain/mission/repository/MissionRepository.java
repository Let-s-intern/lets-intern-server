package com.letsintern.letsintern.domain.mission.repository;

import com.letsintern.letsintern.domain.mission.domain.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {

}
