package com.letsintern.letsintern.domain.attendance.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceCreateDTO;
import com.letsintern.letsintern.domain.attendance.dto.response.AttendanceAdminListResponse;
import com.letsintern.letsintern.domain.attendance.exception.AttendanceAlreadyExists;
import com.letsintern.letsintern.domain.attendance.mapper.AttendanceMapper;
import com.letsintern.letsintern.domain.attendance.repository.AttendanceRepository;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceAdminVo;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceDashboardVo;
import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.mission.exception.MissionNotFound;
import com.letsintern.letsintern.domain.mission.repository.MissionRepository;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AttendanceHelper {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final MissionRepository missionRepository;
    private final ApplicationRepository applicationRepository;

    public Long createAttendance(Long missionId, AttendanceCreateDTO attendanceCreateDTO, User user) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);

        // 이미 출석한 경우 처리
        final Attendance attendance = attendanceRepository.findByMissionIdAndUserId(missionId, user.getId());
        if(attendance != null) throw AttendanceAlreadyExists.EXCEPTION;

        // Mission.attendanceCount++
        mission.setAttendanceCount(mission.getAttendanceCount() + 1);

        return attendanceRepository.save(attendanceMapper.toEntity(mission, attendanceCreateDTO, user)).getId();
    }

    public Page<AttendanceAdminVo> getAttendanceAdminList(Long missionId, Pageable pageable) {
        return attendanceRepository.getAttendanceAdminVos(missionId, pageable);
    }

    public List<AttendanceDashboardVo> getAttendanceDashboardList(Application application) {
        return attendanceRepository.getAttendanceDashboardVos(application.getProgram().getId(), application.getUser().getId());
    }
}
