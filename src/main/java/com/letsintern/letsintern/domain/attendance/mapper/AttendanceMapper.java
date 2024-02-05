package com.letsintern.letsintern.domain.attendance.mapper;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceCreateDTO;
import com.letsintern.letsintern.domain.attendance.dto.response.AttendanceAdminListResponse;
import com.letsintern.letsintern.domain.attendance.dto.response.AttendanceIdResponse;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceAdminVo;
import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {

    public Attendance toEntity(Mission mission, AttendanceCreateDTO attendanceCreateDTO, User user) {
        return Attendance.of(mission, attendanceCreateDTO, user);
    }

    public AttendanceIdResponse toAttendanceIdResponse(Long attendanceId) {
        return AttendanceIdResponse.from(attendanceId);
    }

    public AttendanceAdminListResponse toAttendanceAdminListResponse(Page<AttendanceAdminVo> attendanceAdminList) {
        return AttendanceAdminListResponse.from(attendanceAdminList);
    }
}
