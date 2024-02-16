package com.letsintern.letsintern.domain.attendance.repository;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>, AttendanceRepositoryCustom {
    Attendance findByMissionIdAndUserId(Long missionId, Long id);

    Integer countAllByMissionProgramIdAndMissionThAndStatus(Long programId, Integer missionTh, AttendanceStatus status);
}
