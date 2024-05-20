package com.letsintern.letsintern.domain.attendance;

import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceAdminUpdateDTO;
import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceBaseDTO;
import com.letsintern.letsintern.domain.attendance.dto.response.AccountListResponse;
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
                                                 @RequestBody AttendanceBaseDTO attendanceBaseDTO,
                                                 @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return attendanceService.createAttendance(missionId, attendanceBaseDTO, principalDetails);
    }

    @PatchMapping("/{attendanceId}")
    @Operation(summary = "출석 업데이트")
    public AttendanceIdResponse updateAttendance(@PathVariable Long attendanceId,
                                                 @RequestBody AttendanceBaseDTO attendanceUpdateDTO,
                                                 @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return attendanceService.updateAttendance(attendanceId, attendanceUpdateDTO, principalDetails);
    }

    @GetMapping("/admin")
    @Operation(summary = "어드민 출석 목록 보기")
    public AttendanceAdminListResponse getAttendanceAdminList(@RequestParam(required = false) Long missionId) {
        return attendanceService.getAttendanceAdminList(missionId);
    }

    @PatchMapping("/admin/{attendanceId}")
    @Operation(summary = "어드민 출석 업데이트")
    public AttendanceIdResponse updateAttendanceAdmin(@PathVariable Long attendanceId,
                                                      @RequestBody AttendanceAdminUpdateDTO attendanceAdminUpdateDTO) {
        return attendanceService.updateAttendanceAdmin(attendanceId, attendanceAdminUpdateDTO);
    }

    @GetMapping("/admin/{missionId}/refund")
    @Operation(summary = "어드민 미션 당 보증금 환급 계좌 정보")
    public AccountListResponse getRefundAccountVos(@PathVariable Long missionId) {
        return attendanceService.getAccountListResponse(missionId);
    }

    @GetMapping("/{applicationId}")
    @Operation(summary = "유저 대시보드 - 우리의 기록장 1명 상세보기")
    public AttendanceDashboardResponse getAttendanceDashboardList(@PathVariable Long applicationId,
                                                                  @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return attendanceService.getAttendanceDashboardList(applicationId, principalDetails);
    }
}
