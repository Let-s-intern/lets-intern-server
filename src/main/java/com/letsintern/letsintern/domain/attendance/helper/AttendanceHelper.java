package com.letsintern.letsintern.domain.attendance.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.exception.ApplicationUnauthorized;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceAdminUpdateDTO;
import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceBaseDTO;
import com.letsintern.letsintern.domain.attendance.exception.AttendanceAlreadyExists;
import com.letsintern.letsintern.domain.attendance.exception.AttendanceCannotUpdated;
import com.letsintern.letsintern.domain.attendance.exception.AttendanceNotFound;
import com.letsintern.letsintern.domain.attendance.mapper.AttendanceMapper;
import com.letsintern.letsintern.domain.attendance.repository.AttendanceRepository;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceAdminVo;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceDashboardVo;
import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.mission.exception.MissionNotFound;
import com.letsintern.letsintern.domain.mission.repository.MissionRepository;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.vo.AccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AttendanceHelper {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final MissionRepository missionRepository;
    private final ApplicationRepository applicationRepository;

    public Long createAttendance(Long missionId, AttendanceBaseDTO attendanceBaseDTO, User user) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);
        Application application = applicationRepository.findByProgramIdAndUserId(mission.getProgram().getId(), user.getId());
        if(application == null) throw ApplicationNotFound.EXCEPTION;

        // 이미 출석한 경우 처리
        final Attendance attendance = attendanceRepository.findByMissionIdAndUserId(missionId, user.getId());
        if(attendance != null) throw AttendanceAlreadyExists.EXCEPTION;

        // Mission.attendanceCount++ or lateAttendanceCount++
        if(isLateAttendance(mission.getEndDate())) mission.setLateAttendanceCount(mission.getLateAttendanceCount() + 1);
        else mission.setAttendanceCount(mission.getAttendanceCount() + 1);

        return attendanceRepository.save(attendanceMapper.toEntity(mission, attendanceBaseDTO, user)).getId();
    }

    public Attendance updateAttendance(Long attendanceId, AttendanceBaseDTO attendanceUpdateDTO, Long userId) {
        Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(() -> AttendanceNotFound.EXCEPTION);
        if(!Objects.equals(attendance.getUser().getId(), userId)) throw ApplicationUnauthorized.EXCEPTION;

        if(attendanceUpdateDTO.getLink() != null) {
            if(attendance.getResult().equals(AttendanceResult.WRONG)) {
                attendance.setStatus(AttendanceStatus.UPDATED);
                attendance.setResult(AttendanceResult.WAITING);
            }
            attendance.setLink(attendanceUpdateDTO.getLink());
            attendance.setComments(null);
        }

        return attendance;
    }

    public Integer getPreviousHeadCount(Long programId, Integer missionTh, AttendanceStatus attendanceStatus, AttendanceResult attendanceResult) {
        return attendanceRepository.countAllByMissionProgramIdAndMissionThAndStatusAndResult(programId, missionTh, attendanceStatus, attendanceResult);
    }

    public List<AttendanceAdminVo> getAttendanceAdminList(Long missionId) {
        return attendanceRepository.getAttendanceAdminVos(missionId);
    }

    public List<AttendanceDashboardVo> getAttendanceDashboardList(Application application) {
        return attendanceRepository.getAttendanceDashboardVos(application.getProgram().getId(), application.getUser().getId());
    }

    public Long updateAttendanceAdmin(Long attendanceId, AttendanceAdminUpdateDTO attendanceAdminUpdateDTO) {
        Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(() -> AttendanceNotFound.EXCEPTION);
        Application application = applicationRepository.findByProgramIdAndUserId(attendance.getMission().getProgram().getId(), attendance.getUser().getId());
        if(application == null) throw ApplicationNotFound.EXCEPTION;

        if(attendanceAdminUpdateDTO.getLink() != null) attendance.setLink(attendanceAdminUpdateDTO.getLink());
        if(attendanceAdminUpdateDTO.getStatus() != null) attendance.setStatus(attendanceAdminUpdateDTO.getStatus());
        if(attendanceAdminUpdateDTO.getResult() != null) attendance.setResult(attendanceAdminUpdateDTO.getResult());
        if(attendanceAdminUpdateDTO.getComments() != null) attendance.setComments(attendanceAdminUpdateDTO.getComments());
        if(attendanceAdminUpdateDTO.getIsRefunded() != null) attendance.setIsRefunded(attendanceAdminUpdateDTO.getIsRefunded());

        attendanceRepository.save(attendance);
        return attendance.getId();
    }

    public List<AccountVo> getAccountListResponse(Long missionId) {
        final Mission mission = missionRepository.findById(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);
        return attendanceRepository.getAccountVoList(mission.getId());
    }

    private boolean isLateAttendance(LocalDateTime missionEndDate) {
        return missionEndDate.isBefore(LocalDateTime.now());
    }

    private boolean isEditableAttendance(Mission mission) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(mission.getStartDate()) && now.isBefore(mission.getEndDate());
    }
}
