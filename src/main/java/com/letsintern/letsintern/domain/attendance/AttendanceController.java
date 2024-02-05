package com.letsintern.letsintern.domain.attendance;

import com.letsintern.letsintern.domain.attendance.dto.request.AttendanceCreateDTO;
import com.letsintern.letsintern.domain.attendance.dto.response.AttendanceIdResponse;
import com.letsintern.letsintern.domain.attendance.service.AttendanceService;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
}
