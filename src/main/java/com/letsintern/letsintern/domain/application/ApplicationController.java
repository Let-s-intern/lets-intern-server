package com.letsintern.letsintern.domain.application;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.GuestApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationIdResponseDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationListResponseDTO;
import com.letsintern.letsintern.domain.application.dto.response.UserApplicationListResponseDTO;
import com.letsintern.letsintern.domain.application.service.ApplicationService;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
@Tag(name = "UserApplication")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Operation(summary = "회원 지원서 생성")
    @PostMapping("/create/{programId}")
    public ApplicationIdResponseDTO createUserApplication(
            @PathVariable Long programId,
            @RequestBody ApplicationCreateDTO applicationCreateDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return applicationService.createUserApplication(programId, applicationCreateDTO, principalDetails);
    }

    @Operation(summary = "비회원 지원서 생성")
    @PostMapping("/create/guest/{programId}")
    public ApplicationIdResponseDTO createGuestApplication(
            @PathVariable Long programId,
            @RequestBody GuestApplicationCreateDTO guestApplicationCreateDTO) {
        return applicationService.createGuestApplication(programId, guestApplicationCreateDTO);
    }

    @Operation(summary = "프로그램별 지원서 전체 목록")
    @GetMapping("/list/program/{programId}")
    public ApplicationListResponseDTO getApplicationListOfProgram(@PathVariable Long programId, @PageableDefault(size = 15) Pageable pageable) {
        return applicationService.getApplicationListOfProgram(programId, pageable);
    }

    @Operation(summary = "프로그램별 승인된 지원서 전체 목록")
    @GetMapping("/list/program/approved/{programId}")
    public ApplicationListResponseDTO getApplicationListOfProgramAndApproved(@PathVariable Long programId, @PageableDefault(size = 15) Pageable pageable) {
        return applicationService.getApplicationListOfProgramAndApproved(programId, true, pageable);
    }

    @Operation(summary = "유저별 지원서 전체 목록")
    @GetMapping("/list/user/{userId}")
    public UserApplicationListResponseDTO getApplicationListOfUser(@PathVariable Long userId, @PageableDefault(size = 15) Pageable pageable) {
        return applicationService.getApplicationListOfUser(userId, pageable);
    }
}
