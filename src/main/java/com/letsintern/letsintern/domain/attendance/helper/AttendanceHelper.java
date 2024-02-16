package com.letsintern.letsintern.domain.attendance.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.attendance.domain.Attendance;
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
        Application application = applicationRepository.findByProgramIdAndUserId(mission.getProgram().getId(), user.getId());
        if(application == null) throw ApplicationNotFound.EXCEPTION;

        boolean isLateAttendance = mission.getEndDate().isBefore(LocalDateTime.now());

        // 이미 출석한 경우 처리
        final Attendance attendance = attendanceRepository.findByMissionIdAndUserId(missionId, user.getId());
        if(attendance != null) throw AttendanceAlreadyExists.EXCEPTION;

        // Mission.attendanceCount++, Mission.lateAttendance++
        mission.setAttendanceCount(mission.getAttendanceCount() + 1);
        if(isLateAttendance) mission.setLateAttendanceCount(mission.getLateAttendanceCount() + 1);

        // 보증금 미션인 경우, Application.refund 적립
        if(mission.getType().equals(MissionType.REFUND) && !isLateAttendance) {
            application.setRefund(application.getRefund() + mission.getRefund());
        }

        return attendanceRepository.save(attendanceMapper.toEntity(mission, attendanceCreateDTO, user)).getId();
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
        if(attendanceAdminUpdateDTO.getStatus() != null) {
            if(attendance.getMission().getIsRefunded() == true) {

            }
            if(attendance.getMission().getType().equals(MissionType.REFUND)) {
                // 수정된 출석을 반려하는 경우 (수정 -> attendance.status = UPDATED, mission.attendanceCount++)
                if(attendance.getStatus().equals(AttendanceStatus.UPDATED) && attendanceAdminUpdateDTO.getStatus().equals(AttendanceStatus.WRONG)) {
                    application.setRefund(application.getRefund() - attendance.getMission().getRefund());           // 환급 금액에서 제외하기
                    attendance.getMission().setAttendanceCount(attendance.getMission().getAttendanceCount() - 1);   // 출석 개수에서 제외하기
                    attendance.setComments(null);                                                                   // 출석 코멘트 삭제하기
                }

                // 그 외 출석을 반려하는 경우
                if(!attendance.getStatus().equals(AttendanceStatus.WRONG) && attendanceAdminUpdateDTO.getStatus().equals(AttendanceStatus.WRONG)) {
                    application.setRefund(application.getRefund() - attendance.getMission().getRefund());           // 환급 금액에서 제외하기
                    attendance.getMission().setAttendanceCount(attendance.getMission().getAttendanceCount() - 1);   // 출석 개수에서 제외하기
                }

            }
            attendance.setStatus(attendanceAdminUpdateDTO.getStatus());
        }
        if(attendanceAdminUpdateDTO.getComments() != null) attendance.setComments(attendanceAdminUpdateDTO.getComments());
        if(attendanceAdminUpdateDTO.getIsRefunded() != null) attendance.setIsRefunded(attendanceAdminUpdateDTO.getIsRefunded());

        return attendance.getId();
    }
}
