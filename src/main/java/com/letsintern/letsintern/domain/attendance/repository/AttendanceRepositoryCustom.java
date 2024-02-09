package com.letsintern.letsintern.domain.attendance.repository;

import com.letsintern.letsintern.domain.attendance.vo.AttendanceAdminVo;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceDashboardVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttendanceRepositoryCustom {

    Page<AttendanceAdminVo> getAttendanceAdminVos(Long missionId, Pageable pageable);

    List<AttendanceDashboardVo> getAttendanceDashboardVos(Long programId, Long userId);

}
