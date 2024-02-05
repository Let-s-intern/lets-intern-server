package com.letsintern.letsintern.domain.attendance.helper;

import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceCreateDTO;
import com.letsintern.letsintern.domain.attendance.dto.response.AttendanceAdminListResponse;
import com.letsintern.letsintern.domain.attendance.mapper.AttendanceMapper;
import com.letsintern.letsintern.domain.attendance.repository.AttendanceRepository;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceAdminVo;
import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.mission.exception.MissionNotFound;
import com.letsintern.letsintern.domain.mission.repository.MissionRepository;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AttendanceHelper {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final MissionRepository missionRepository;

    public Long createAttendance(Long missionId, AttendanceCreateDTO attendanceCreateDTO, User user) {
        final Mission mission = missionRepository.findById(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);
        // 이미 출석한 경우 처리
        // 지각 제출 처리
        // 제출 불가능한 경우 처리
        // Mission.attendanceCount++
        return attendanceRepository.save(attendanceMapper.toEntity(mission, attendanceCreateDTO, user)).getId();
    }

    public Page<AttendanceAdminVo> getAttendanceAdminList(Long missionId, Pageable pageable) {
        return attendanceRepository.getAttendanceAdminVos(missionId, pageable);
    }
}
