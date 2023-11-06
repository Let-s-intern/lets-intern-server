package com.letsintern.letsintern.domain.application.mapper;

import com.letsintern.letsintern.domain.application.domain.GuestApplication;
import com.letsintern.letsintern.domain.application.domain.UserApplication;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.request.GuestApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationIdResponseDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationListResponseDTO;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationMapper {

    private final ProgramRepository programRepository;

    public UserApplication toUserEntity(Long programId, ApplicationCreateDTO applicationCreateDTO, User user) {
        return UserApplication.of(
                validateApply(programId, user.getPhoneNum()),
                user,
                applicationCreateDTO
        );
    }

    public GuestApplication toGuestEntity(Long programId, GuestApplicationCreateDTO guestApplicationCreateDTO) {
        return GuestApplication.of(
                validateApply(programId, guestApplicationCreateDTO.getGuestPhoneNum()),
                guestApplicationCreateDTO
        );
    }

    public ApplicationIdResponseDTO toApplicationIdResponse(Long applicationId) {
        return ApplicationIdResponseDTO.from(applicationId);
    }

    public ApplicationListResponseDTO toApplicationListResponseDTO(List<UserApplication> userApplicationList) {
        return ApplicationListResponseDTO.from(userApplicationList);
    }

    private Program validateApply(Long programId, String phoneNum) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });

        // 기존 신청 내역 존재하는지 판단 (Application)

        return program;
    }
}
