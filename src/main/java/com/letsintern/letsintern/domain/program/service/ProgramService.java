package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.response.BaseProgramResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ProgramDetailResponseDto;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListResponseDto;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import org.springframework.stereotype.Service;

@Service
public interface ProgramService {
    BaseProgramResponseDto<?> getProgramForAdmin(Long programId);
    ProgramListResponseDto getProgramList();
    ProgramDetailResponseDto<?> getProgramDetail(Long programId, PrincipalDetails principalDetails);
    void createProgram(BaseProgramRequestDto requestDto);
    void updateProgram(Long programId, BaseProgramRequestDto requestDto);
    void deleteProgram(Long programId);
}
