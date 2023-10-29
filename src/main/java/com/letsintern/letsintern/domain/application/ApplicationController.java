package com.letsintern.letsintern.domain.application;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationIdResponseDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationListResponseDTO;
import com.letsintern.letsintern.domain.application.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public ApplicationListResponseDTO getApplicationListOfProgram(@PathVariable Long programId) {
        return applicationService.getApplicationListOfProgram(programId);
    }

    @Operation(summary = "유저별 지원서 전체 목록")
    @GetMapping("/list/user/{userId}")
    public ApplicationListResponseDTO getApplicationListOfUser(@PathVariable Long userId) {
        return applicationService.getApplicationListOfUser(userId);
    }
}
