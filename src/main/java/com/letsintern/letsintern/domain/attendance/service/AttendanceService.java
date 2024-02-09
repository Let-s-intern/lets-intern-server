package com.letsintern.letsintern.domain.attendance.service;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceCreateDTO;
import com.letsintern.letsintern.domain.attendance.dto.response.AttendanceAdminListResponse;
import com.letsintern.letsintern.domain.attendance.dto.response.AttendanceDashboardResponse;
import com.letsintern.letsintern.domain.attendance.dto.response.AttendanceIdResponse;
import com.letsintern.letsintern.domain.attendance.helper.AttendanceHelper;
import com.letsintern.letsintern.domain.attendance.mapper.AttendanceMapper;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceHelper attendanceHelper;
    private final AttendanceMapper attendanceMapper;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public AttendanceIdResponse createAttendance(Long missionId, AttendanceCreateDTO attendanceCreateDTO, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return attendanceMapper.toAttendanceIdResponse(attendanceHelper.createAttendance(missionId, attendanceCreateDTO, user));
    }

    @Transactional(readOnly = true)
    public AttendanceAdminListResponse getAttendanceAdminList(Long missionId, Pageable pageable) {
        return attendanceMapper.toAttendanceAdminListResponse(attendanceHelper.getAttendanceAdminList(missionId, pageable));
    }

    @Transactional(readOnly = true)
    public AttendanceDashboardResponse getAttendanceDashboardList(Long applicationId) {
        final Application application = applicationRepository.findById(applicationId).orElseThrow(() -> ApplicationNotFound.EXCEPTION);
        return attendanceMapper.toAttendanceDashboardResponse(
                application.getUser().getName(),
                application.getWishJob(),
                application.getIntroduction(),
                attendanceHelper.getAttendanceDashboardList(application)
        );
    }
}
