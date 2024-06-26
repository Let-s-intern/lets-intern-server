package com.letsintern.letsintern.domain.application.mapper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.application.dto.response.*;
import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationChallengeAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationVo;
import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationMapper {
    private final ProgramRepository programRepository;

    public Application toEntity(Long programId, ApplicationCreateDTO applicationCreateDTO, User user, Integer totalFee, String name) {
        return Application.of(validateProgram(programId), user, applicationCreateDTO, totalFee, name);
    }

    public ApplicationIdResponse toApplicationIdResponse(Long applicationId) {
        return ApplicationIdResponse.from(applicationId);
    }

    public ApplicationCreateResponse toApplicationCreateResponse(Application application) {
        return ApplicationCreateResponse.from(application);
    }

    public AdminApplicationListResponse toAdminApplicationListResponse(List<ApplicationAdminVo> applicationList) {
        return AdminApplicationListResponse.from(applicationList);
    }

    public ApplicationListResponse toApplicationListResponse(Page<Application> applicationList) {
        return ApplicationListResponse.from(applicationList);
    }

    public UserApplicationListResponse toUserApplicationListResponse(List<ApplicationVo> userApplicationList) {
        return UserApplicationListResponse.from(userApplicationList);
    }

    public EmailListResponse toEmailListResponse(List<String> approvedEmailList, List<String> notApprovedEmailList) {
        return EmailListResponse.of(approvedEmailList, notApprovedEmailList);
    }

    private Program validateProgram(Long programId) {
        return programRepository.findById(programId)
                .orElseThrow(() -> {
                    throw ProgramNotFound.EXCEPTION;
                });
    }

    public ApplicationChallengeAdminVosResponse toApplicationChallengeAdminVosResponse(Page<ApplicationChallengeAdminVo> applicationList) {
        return ApplicationChallengeAdminVosResponse.from(applicationList);
    }
}
