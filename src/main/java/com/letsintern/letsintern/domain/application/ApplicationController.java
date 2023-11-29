package com.letsintern.letsintern.domain.application;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationUpdateDTO;
import com.letsintern.letsintern.domain.application.dto.response.*;
import com.letsintern.letsintern.domain.application.service.ApplicationService;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
@Tag(name = "Application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Operation(summary = "지원서 생성")
    @PostMapping("/{programId}")
    public ApplicationCreateResponse createUserApplication(
            @PathVariable Long programId,
            @RequestBody ApplicationCreateDTO applicationCreateDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        // 비회원 지원서 생성
        if(principalDetails == null) {
            return applicationService.createGuestApplication(programId, applicationCreateDTO);
        }

        // 회원 지원서 생성
        return applicationService.createUserApplication(programId, applicationCreateDTO, principalDetails);
    }

    @Operation(summary = "마이페이지 나의 지원서 목록")
    @GetMapping("")
    public UserApplicationListResponse getMyPageApplicationList(
            @PageableDefault(size = 20) Pageable pageable,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return applicationService.getApplicationListOfUser(user.getId(), pageable);
    }

    @Operation(summary = "마이페이지 지원 내역 취소")
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long applicationId) {
        applicationService.deleteApplication(applicationId);
        return ResponseEntity.ok("success");
    }

    @Operation(summary = "어드민 프로그램별 지원서 전체 목록")
    @GetMapping("/admin/{programId}")
    public AdminApplicationListResponse getApplicationListOfProgram(
            @PathVariable Long programId,
            @RequestParam(required = false) Boolean isApproved,
            @PageableDefault(size = 15) Pageable pageable) {

        if(isApproved != null) return applicationService.getApplicationListOfProgramAndApproved(programId, isApproved, pageable);
        return applicationService.getApplicationListOfProgram(programId, pageable);
    }

    @Operation(summary = "어드민 사용자 1명의 지원서 전체 목록")
    @GetMapping("/admin/user/{userId}")
    public ApplicationListResponse getAdminApplicationListOfUserId(@PathVariable Long userId) {
        return applicationService.getAdminApplicationListOfUserId(userId);
    }

    @Operation(summary = "어드민 지원서 상태 변경")
    @PatchMapping("/{applicationId}")
    public ApplicationIdResponse updateApplicationStatus(@PathVariable Long applicationId, @RequestBody ApplicationUpdateDTO applicationUpdateDTO) {
        return applicationService.updateApplication(applicationId, applicationUpdateDTO);
    }

    @Operation(summary = "어드민 미선발 지원자 상태 변경 (한번에)")
    @GetMapping("/admin/not-approved/{programId}")
    public String updateNotApprovedStatus(@PathVariable Long programId) {
        return applicationService.updateApplicationNotApproved(programId);
    }

    @Operation(summary = "어드민 선발/미선발 이메일 목록")
    @GetMapping("/admin/email/{programId}")
    public EmailListResponse getEmailList(@PathVariable Long programId) {
        return applicationService.getEmailList(programId);
    }
}
