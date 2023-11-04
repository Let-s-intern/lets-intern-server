package com.letsintern.letsintern.domain.application;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationIdResponseDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationListResponseDTO;
import com.letsintern.letsintern.domain.application.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
@Tag(name = "Application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Operation(summary = "지원서 생성")
    @PostMapping("/create")
    public ApplicationIdResponseDTO createApplication(@RequestBody ApplicationCreateDTO applicationCreateDTO) {
        return applicationService.createApplication(applicationCreateDTO);
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
    public ApplicationListResponseDTO getApplicationListOfUser(@PathVariable Long userId, @PageableDefault(size = 15) Pageable pageable) {
        return applicationService.getApplicationListOfUser(userId, pageable);
    }
}
