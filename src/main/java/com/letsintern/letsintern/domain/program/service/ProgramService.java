package com.letsintern.letsintern.domain.program.service;

import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramIdResponseDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramTotalListDTO;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramHelper programHelper;
    private final ProgramMapper programMapper;


    @Transactional
    public ProgramIdResponseDTO createProgram(ProgramCreateRequestDTO programCreateRequestDTO) {
        return programMapper.toProgramIdResponseDTO(programHelper.createProgram(programCreateRequestDTO));
    }

    @Transactional
    public ProgramTotalListDTO getProgramTotalList() {
        return programMapper.toProgramTotalListDTO(programHelper.getProgramTotalList());
    }
}
