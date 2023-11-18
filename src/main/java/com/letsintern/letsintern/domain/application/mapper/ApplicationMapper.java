package com.letsintern.letsintern.domain.application.mapper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.GuestApplication;
import com.letsintern.letsintern.domain.application.domain.UserApplication;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationCreateResponse;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationIdResponse;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationListResponse;
import com.letsintern.letsintern.domain.application.dto.response.UserApplicationListResponse;
import com.letsintern.letsintern.domain.application.vo.UserApplicationVo;
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

    public GuestApplication toGuestEntity(Long programId, ApplicationCreateDTO applicationCreateDTO) {
        return GuestApplication.of(
                validateApply(programId, applicationCreateDTO.getGuestPhoneNum()),
                applicationCreateDTO
        );
    }

    public ApplicationIdResponse toApplicationIdResponse(Long applicationId) {
        return ApplicationIdResponse.from(applicationId);
    }

    public ApplicationCreateResponse toApplicationCreateResponse(Application application) {
        return ApplicationCreateResponse.from(application.getId(), application.getProgram().getAnnouncementDate());
    }

    public ApplicationListResponse toApplicationListResponseDTO(List<Application> applicationList) {
        return ApplicationListResponse.from(applicationList);
    }

    public UserApplicationListResponse toUserApplicationListResponse(List<UserApplicationVo> userApplicationList) {
        return UserApplicationListResponse.from(userApplicationList);
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
