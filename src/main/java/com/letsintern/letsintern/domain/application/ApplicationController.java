package com.letsintern.letsintern.domain.application;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.GuestApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationIdResponseDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationListResponseDTO;
import com.letsintern.letsintern.domain.application.dto.response.UserApplicationListResponseDTO;
import com.letsintern.letsintern.domain.application.service.ApplicationService;
import com.letsintern.letsintern.domain.user.domain.User;
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
@Tag(name = "Application")
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
    @PostMapping("/guest/create/{programId}")
    public ApplicationIdResponseDTO createGuestApplication(
            @PathVariable Long programId,
            @RequestBody GuestApplicationCreateDTO guestApplicationCreateDTO) {
        return applicationService.createGuestApplication(programId, guestApplicationCreateDTO);
    }

    @Operation(summary = "마이페이지 나의 지원서 목록")
    @GetMapping("/list/mypage")
    public UserApplicationListResponseDTO getMyPageApplicationList(
            @PageableDefault(size = 15) Pageable pageable,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return applicationService.getApplicationListOfUser(user.getId(), pageable);
    }


    @Operation(summary = "어드민 프로그램별 지원서 전체 목록")
    @GetMapping("/admin/list/program/{programId}")
    public ApplicationListResponseDTO getApplicationListOfProgram(@PathVariable Long programId, @PageableDefault(size = 15) Pageable pageable) {
        return applicationService.getApplicationListOfProgram(programId, pageable);
    }

    @Operation(summary = "어드민 프로그램별 승인된 지원서 전체 목록")
    @GetMapping("/admin/list/program/approved/{programId}")
    public ApplicationListResponseDTO getApplicationListOfProgramAndApproved(@PathVariable Long programId, @PageableDefault(size = 15) Pageable pageable) {
        return applicationService.getApplicationListOfProgramAndApproved(programId, true, pageable);
    }

    @Operation(summary = "어드민 유저별 지원서 전체 목록")
    @GetMapping("/admin/list/user/{userId}")
    public UserApplicationListResponseDTO getApplicationListOfUser(@PathVariable Long userId, @PageableDefault(size = 15) Pageable pageable) {
        return applicationService.getApplicationListOfUser(userId, pageable);
    }

    @Operation(summary = "어드민 지원서 상태 변경")
    @GetMapping("/admin/update/{applicationId}/{approved}")
    public ApplicationIdResponseDTO updateApplicationStatus(@PathVariable Long applicationId, @PathVariable Boolean approved) {
        return applicationService.updateApplicationApproved(applicationId, approved);
    }

}
