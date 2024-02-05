package com.letsintern.letsintern.domain.attendance.repository;

import com.letsintern.letsintern.domain.attendance.vo.AttendanceAdminVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AttendanceRepositoryCustom {

    Page<AttendanceAdminVo> getAttendanceAdminVos(Long missionId, Pageable pageable);
}
