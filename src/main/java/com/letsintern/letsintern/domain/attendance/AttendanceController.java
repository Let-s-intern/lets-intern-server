package com.letsintern.letsintern.domain.attendance;

import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceCreateDTO;
import com.letsintern.letsintern.domain.attendance.dto.response.AttendanceAdminListResponse;
import com.letsintern.letsintern.domain.attendance.dto.response.AttendanceDashboardResponse;
import com.letsintern.letsintern.domain.attendance.dto.response.AttendanceIdResponse;
import com.letsintern.letsintern.domain.attendance.service.AttendanceService;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
@Tag(name = "Attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/{missionId}")
    @Operation(summary = "출석 생성")
    public AttendanceIdResponse createAttendance(@PathVariable Long missionId,
                                                 @RequestBody AttendanceCreateDTO attendanceCreateDTO,
                                                 @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return attendanceService.createAttendance(missionId, attendanceCreateDTO, principalDetails);
    }

    @GetMapping("")
    @Operation(summary = "어드민 출석 목록 보기")
    public AttendanceAdminListResponse getAttendanceAdminList(@RequestParam(required = false) Long missionId, @PageableDefault(size = 20) Pageable pageable) {
        return attendanceService.getAttendanceAdminList(missionId, pageable);
    }

    @GetMapping("/{applicationId}")
    @Operation(summary = "유저 대시보드 - 우리의 기록장 1명 상세보기")
    public AttendanceDashboardResponse getAttendanceDashboardList(@PathVariable Long applicationId) {
        return attendanceService.getAttendanceDashboardList(applicationId);
    }

    // [어드민] 보증금 미션 Attendance status PASSED로 변경 시 Application.refund에 환급 금액 더하기
}
