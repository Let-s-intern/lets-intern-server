package com.letsintern.letsintern.domain.application;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationChallengeUpdateDTO;
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

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
@Tag(name = "Application")
public class ApplicationController {
    private final ApplicationService applicationService;

    @Operation(summary = "지원서 생성")
    @PostMapping("/{programId}")
    public ApplicationCreateResponse createUserApplication(@PathVariable Long programId,
                                                           @RequestBody ApplicationCreateDTO applicationCreateDTO,
                                                           @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (Objects.isNull(principalDetails))
            return applicationService.createGuestApplication(programId, applicationCreateDTO);
        else
            return applicationService.createUserApplication(programId, applicationCreateDTO, principalDetails);
    }

    @Operation(summary = "마이페이지 나의 지원서 목록")
    @GetMapping
    public UserApplicationListResponse getMyPageApplicationList(@PageableDefault(size = 1000) Pageable pageable,
                                                                @AuthenticationPrincipal PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return applicationService.getApplicationListOfUser(user.getId(), pageable);
    }

    @Operation(summary = "마이페이지 지원 내역 취소")
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<String> deleteApplication(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                    @PathVariable Long applicationId) {
        applicationService.deleteApplication(applicationId, principalDetails);
        return ResponseEntity.ok("success");
    }

    @Operation(summary = "해당 유저가 유효한 참여자인지 여부 확인")
    @GetMapping("/{programId}")
    public ApplicationValidityResponse checkApplicationValidity(@PathVariable Long programId,
                                                                @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return applicationService.checkApplicationValidity(programId, principalDetails);
    }

    @Operation(summary = "챌린지 한줄 소개, 희망 직무 수정")
    @PatchMapping("/{applicationId}/challenge")
    public ApplicationIdResponse updateChallengeApplication(@PathVariable Long applicationId,
                                                            @RequestBody ApplicationChallengeUpdateDTO applicationChallengeUpdateDTO,
                                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return applicationService.updateChallengeApplication(applicationId, applicationChallengeUpdateDTO, principalDetails);
    }

    @Operation(summary = "어드민 프로그램별 지원서 전체 목록")
    @GetMapping("/admin/{programId}")
    public AdminApplicationListResponse getApplicationListOfProgram(@PathVariable Long programId,
                                                                    @RequestParam(required = false) Boolean isApproved,
                                                                    @PageableDefault(size = 1000) Pageable pageable) {
        if (isApproved != null) {
            return applicationService.getApplicationListOfProgramAndApproved(programId, isApproved, pageable);
        } else {
            return applicationService.getApplicationListOfProgram(programId, pageable);
        }
    }

    @Operation(summary = "어드민 사용자 1명의 지원서 전체 목록")
    @GetMapping("/admin/user/{userId}")
    public ApplicationListResponse getAdminApplicationListOfUserId(@PathVariable Long userId,
                                                                   @PageableDefault(size = 1000) Pageable pageable) {
        return applicationService.getAdminApplicationListOfUserId(userId, pageable);
    }

    @Operation(summary = "어드민 지원서 상태 변경")
    @PatchMapping("/admin/{applicationId}")
    public ApplicationIdResponse updateApplicationStatus(@PathVariable Long applicationId,
                                                         @RequestBody ApplicationUpdateDTO applicationUpdateDTO) {
        return applicationService.updateApplication(applicationId, applicationUpdateDTO);
    }

    @Operation(summary = "어드민 선발/미선발 이메일 목록")
    @GetMapping("/admin/email/{programId}")
    public EmailListResponse getEmailList(@PathVariable Long programId) {
        return applicationService.getEmailList(programId);
    }

    @Operation(summary = "어드민 챌린지 대시보드 참여자 목록")
    @GetMapping("/admin/challenge/{programId}")
    public ApplicationChallengeAdminVosResponse getApplicationChallengeAdminList(@PathVariable Long programId,
                                                                                 @PageableDefault(size = 10) Pageable pageable,
                                                                                 @RequestParam(required = false) String name,
                                                                                 @RequestParam(required = false) String email,
                                                                                 @RequestParam(required = false) String phoneNum) {
        return applicationService.getApplicationChallengeAdminList(programId, pageable, name, email, phoneNum);
    }

    @Operation(summary = "어드민 챌린지 대시보드 참여자 목록 1건 상세")
    @GetMapping("/admin/challenge/detail/{applicationId}")
    public ApplicationChallengeAdminVoDetail getApplicationChallengeAdminDetail(@PathVariable Long applicationId) {
        return applicationService.getApplicationChallengeAdminDetail(applicationId);
    }
}
