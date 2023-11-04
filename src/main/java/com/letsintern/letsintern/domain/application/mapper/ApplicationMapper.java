package com.letsintern.letsintern.domain.application.mapper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationIdResponseDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationListResponseDTO;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationMapper {

    private final ProgramRepository programRepository;
    private final UserRepository userRepository;

    public Application toEntity(ApplicationCreateDTO applicationCreateDTO) {
        Program program = programRepository.findById(applicationCreateDTO.getProgramId())
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });

        User user = userRepository.findById(applicationCreateDTO.getUserId())
                .orElseThrow(() -> {
                    throw ApplicationNotFound.EXCEPTION;
                });

        return Application.of(program, user, applicationCreateDTO);
    }

    public ApplicationIdResponseDTO toApplicationIdResponse(Long applicationId) {
        return ApplicationIdResponseDTO.from(applicationId);
    }

    public ApplicationListResponseDTO toApplicationListResponseDTO(List<Application> applicationList) {
        return ApplicationListResponseDTO.from(applicationList);
    }
}
