package com.letsintern.letsintern.domain.attendance.service;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceAdminUpdateDTO;
import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceBaseDTO;
import com.letsintern.letsintern.domain.attendance.dto.response.AccountListResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AttendanceService {
    private final AttendanceHelper attendanceHelper;
    private final AttendanceMapper attendanceMapper;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public AttendanceIdResponse createAttendance(Long missionId, AttendanceBaseDTO attendanceBaseDTO, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return attendanceMapper.toAttendanceIdResponse(attendanceHelper.createAttendance(missionId, attendanceBaseDTO, user));
    }

    @Transactional
    public AttendanceIdResponse updateAttendance(Long attendanceId, AttendanceBaseDTO attendanceUpdateDTO, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        Attendance updatedAttendance = attendanceHelper.updateAttendance(attendanceId, attendanceUpdateDTO, user.getId());
        return attendanceMapper.toAttendanceIdResponse(updatedAttendance.getId());
    }

    @Transactional(readOnly = true)
    public AttendanceAdminListResponse getAttendanceAdminList(Long missionId) {
        return attendanceMapper.toAttendanceAdminListResponse(attendanceHelper.getAttendanceAdminList(missionId));
    }

    @Transactional
    public AttendanceIdResponse updateAttendanceAdmin(Long attendanceId, AttendanceAdminUpdateDTO attendanceAdminUpdateDTO) {
        return attendanceMapper.toAttendanceIdResponse(attendanceHelper.updateAttendanceAdmin(attendanceId, attendanceAdminUpdateDTO));
    }

    @Transactional(readOnly = true)
    public AttendanceDashboardResponse getAttendanceDashboardList(Long applicationId, PrincipalDetails principalDetails) {
        final Application application = applicationRepository.findById(applicationId).orElseThrow(() -> ApplicationNotFound.EXCEPTION);
        final User user = principalDetails.getUser();
        boolean isMine = Objects.equals(application.getUser().getId(), user.getId());
        List<ApplicationWishJob> wishJobList = isMine ? ApplicationWishJob.getApplicationWishJobListByProgramTopic(application.getProgram().getTopic()) : new ArrayList<>();
        return attendanceMapper.toAttendanceDashboardResponse(
                application.getUser().getName(),
                application.getWishJob(),
                application.getIntroduction(),
                isMine,
                wishJobList,
                attendanceHelper.getAttendanceDashboardList(application)
        );
    }

    public AccountListResponse getAccountListResponse(Long missionId) {
        return attendanceMapper.toAccountListResponse(attendanceHelper.getAccountListResponse(missionId));
    }
}
