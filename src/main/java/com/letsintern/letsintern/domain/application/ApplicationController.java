package com.letsintern.letsintern.domain.application;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationIdResponseDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationListResponseDTO;
import com.letsintern.letsintern.domain.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/create")
    public ApplicationIdResponseDTO createApplication(@RequestBody ApplicationCreateDTO applicationCreateDTO) {
        return applicationService.createApplication(applicationCreateDTO);
    }

    @GetMapping("/list/program/{programId}")
    public ApplicationListResponseDTO getApplicationListOfProgram(@PathVariable Long programId) {
        return applicationService.getApplicationListOfProgram(programId);
    }

    @GetMapping("/list/user/{userId}")
    public ApplicationListResponseDTO getApplicationListOfUser(@PathVariable Long userId) {
        return applicationService.getApplicationListOfUser(userId);
    }
}
