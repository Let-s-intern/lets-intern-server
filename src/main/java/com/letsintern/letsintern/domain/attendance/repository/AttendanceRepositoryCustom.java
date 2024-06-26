package com.letsintern.letsintern.domain.attendance.repository;

import com.letsintern.letsintern.domain.attendance.vo.AttendanceAdminVo;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceDashboardVo;
import com.letsintern.letsintern.domain.user.vo.AccountVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttendanceRepositoryCustom {

    List<AttendanceAdminVo> getAttendanceAdminVos(Long missionId);

    List<AttendanceDashboardVo> getAttendanceDashboardVos(Long programId, Long userId);

    List<AccountVo> getAccountVoList(Long missionId);

    long countNotCheckedAttendances(Long missionId);

    long countNotRefundedAttendances(Long missionId);

}
