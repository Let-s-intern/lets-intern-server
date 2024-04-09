package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.program.dto.request.BaseProgramRequestDto;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ProgramService {
    ProgramListResponseDto getProgramList();
    void createProgram(BaseProgramRequestDto requestDto);
    void updateProgram(BaseProgramRequestDto requestDto);
    void deleteProgram(Long programId);
}
