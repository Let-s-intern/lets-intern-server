package com.letsintern.letsintern.domain.attendance.service;

import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceCreateDTO;
import com.letsintern.letsintern.domain.attendance.dto.response.AttendanceIdResponse;
import com.letsintern.letsintern.domain.attendance.helper.AttendanceHelper;
import com.letsintern.letsintern.domain.attendance.mapper.AttendanceMapper;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceHelper attendanceHelper;
    private final AttendanceMapper attendanceMapper;

    @Transactional
    public AttendanceIdResponse createAttendance(Long missionId, AttendanceCreateDTO attendanceCreateDTO, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return attendanceMapper.toAttendanceIdResponse(attendanceHelper.createAttendance(missionId, attendanceCreateDTO, user));
    }
}
