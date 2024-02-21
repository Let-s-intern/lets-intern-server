package com.letsintern.letsintern.domain.attendance.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.exception.ApplicationUnauthorized;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceResult;
import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceAdminUpdateDTO;
import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceCreateDTO;
import com.letsintern.letsintern.domain.attendance.exception.AttendanceAlreadyExists;
import com.letsintern.letsintern.domain.attendance.exception.AttendanceNotFound;
import com.letsintern.letsintern.domain.attendance.mapper.AttendanceMapper;
import com.letsintern.letsintern.domain.attendance.repository.AttendanceRepository;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceAdminVo;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceDashboardVo;
import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.mission.domain.MissionType;
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

    public Long createAttendance(Long missionId, AttendanceCreateDTO attendanceCreateDTO, User user) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);
        Application application = applicationRepository.findByProgramIdAndUserId(mission.getProgram().getId(), user.getId());
        if(application == null) throw ApplicationNotFound.EXCEPTION;

        boolean isLateAttendance = mission.getEndDate().isBefore(LocalDateTime.now());

        // 이미 출석한 경우 처리
        final Attendance attendance = attendanceRepository.findByMissionIdAndUserId(missionId, user.getId());
        if(attendance != null) throw AttendanceAlreadyExists.EXCEPTION;

        // Mission.attendanceCount++ or lateAttendanceCount++
        if(isLateAttendance) mission.setLateAttendanceCount(mission.getLateAttendanceCount() + 1);
        else mission.setAttendanceCount(mission.getAttendanceCount() + 1);

        // 보증금 미션인 경우, Application.refund 적립
        if(mission.getType().equals(MissionType.REFUND) && !isLateAttendance) {
            application.setRefund(application.getRefund() + mission.getRefund());
        }

        return attendanceRepository.save(attendanceMapper.toEntity(mission, attendanceCreateDTO, user)).getId();
    }

    public Long updateAttendance(Long attendanceId, AttendanceCreateDTO attendanceUpdateDTO, Long userId) {
        Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(() -> AttendanceNotFound.EXCEPTION);
        if(!Objects.equals(attendance.getUser().getId(), userId)) throw ApplicationUnauthorized.EXCEPTION;
        else if(attendanceUpdateDTO.getLink() != null) {
            attendance.setLink(attendanceUpdateDTO.getLink());
            attendance.setStatus(AttendanceStatus.UPDATED);
            attendance.setResult(AttendanceResult.WAITING);
        }
        return attendance.getId();
    }

    public Page<AttendanceAdminVo> getAttendanceAdminList(Long missionId, Pageable pageable) {
        return attendanceRepository.getAttendanceAdminVos(missionId, pageable);
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
        if(attendanceAdminUpdateDTO.getResult() != null) {
            if(attendance.getStatus().equals(AttendanceStatus.UPDATED) && attendanceAdminUpdateDTO.getResult().equals(AttendanceResult.WRONG)) {
                attendance.setComments(null);
            }

            // 보증금 미션에서 반려된 경우
            if(attendance.getMission().getType().equals(MissionType.REFUND)
                    && attendance.getStatus().equals(AttendanceStatus.PRESENT) && attendanceAdminUpdateDTO.getResult().equals(AttendanceResult.WRONG)) {
                application.setRefund(application.getRefund() - attendance.getMission().getRefund());
            }
            attendance.setResult(attendanceAdminUpdateDTO.getResult());
        }
        if(attendanceAdminUpdateDTO.getComments() != null) attendance.setComments(attendanceAdminUpdateDTO.getComments());
        if(attendanceAdminUpdateDTO.getIsRefunded() != null) attendance.setIsRefunded(attendanceAdminUpdateDTO.getIsRefunded());

        return attendance.getId();
    }

    public List<AccountVo> getAccountListResponse(Long missionId) {
        final Mission mission = missionRepository.findById(missionId).orElseThrow(() -> MissionNotFound.EXCEPTION);
        return attendanceRepository.getAccountVoList(mission.getId());
    }
}
