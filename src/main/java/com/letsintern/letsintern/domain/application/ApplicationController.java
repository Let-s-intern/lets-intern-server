package com.letsintern.letsintern.domain.application;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationCreateResponse;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationIdResponse;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationListResponse;
import com.letsintern.letsintern.domain.application.dto.response.UserApplicationListResponse;
import com.letsintern.letsintern.domain.application.exception.ApplicationUserBadRequest;
import com.letsintern.letsintern.domain.application.service.ApplicationService;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.service.UserService;
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
    private final UserService userService;

    @Operation(summary = "기존 신청 유무, 지원자 추가 정보 유무 확인")
    @GetMapping("/{programId}")
    public ResponseEntity<Boolean> checkUserApplicationHistory(
            @PathVariable Long programId,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        applicationService.checkUserApplicationHistory(programId, principalDetails);
        return ResponseEntity.ok(null);
    }

    @Operation(summary = "지원서 생성")
    @PostMapping("/{programId}")
    public ApplicationCreateResponse createUserApplication(
            @PathVariable Long programId,
            @RequestParam(required = false) Boolean userDetailInfo,
            @RequestBody ApplicationCreateDTO applicationCreateDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        // 비회원 지원서 생성
        if(principalDetails == null) {
            return applicationService.createGuestApplication(programId, applicationCreateDTO);
        }

        // 회원 상세 정보 추가
        if(userDetailInfo == true) {
            if(applicationCreateDTO.getUniversity() == null || applicationCreateDTO.getMajor() == null) {
                throw ApplicationUserBadRequest.EXCEPTION;
            }
            userService.addUserDetailInfo(principalDetails, applicationCreateDTO.getUniversity(), applicationCreateDTO.getMajor());
        }

        // 회원 지원서 생성
        return applicationService.createUserApplication(programId, applicationCreateDTO, principalDetails);
    }

    @Operation(summary = "마이페이지 나의 지원서 목록")
    @GetMapping("")
    public UserApplicationListResponse getMyPageApplicationList(
            @PageableDefault(size = 15) Pageable pageable,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return applicationService.getApplicationListOfUser(user.getId(), pageable);
    }


    @Operation(summary = "어드민 프로그램별 지원서 전체 목록")
    @GetMapping("/admin/{programId}")
    public ApplicationListResponse getApplicationListOfProgram(
            @PathVariable Long programId,
            @RequestParam(required = false) Boolean approved,
            @PageableDefault(size = 15) Pageable pageable) {

        if(approved != null) return applicationService.getApplicationListOfProgramAndApproved(programId, approved, pageable);
        return applicationService.getApplicationListOfProgram(programId, pageable);
    }

    @Operation(summary = "어드민 유저별 지원서 전체 목록")
    @GetMapping("/admin/{userId}")
    public UserApplicationListResponse getApplicationListOfUser(@PathVariable Long userId, @PageableDefault(size = 15) Pageable pageable) {
        return applicationService.getApplicationListOfUser(userId, pageable);
    }

    @Operation(summary = "어드민 지원서 상태 변경")
    @GetMapping("/admin/{applicationId}")
    public ApplicationIdResponse updateApplicationStatus(@PathVariable Long applicationId, @RequestParam(required = true) Boolean approved) {
        return applicationService.updateApplicationApproved(applicationId, approved);
    }

}
