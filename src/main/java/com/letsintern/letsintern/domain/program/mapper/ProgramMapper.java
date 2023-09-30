package com.letsintern.letsintern.domain.program.mapper;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramIdResponseDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramTotalListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgramMapper {

    public Program toEntity(ProgramCreateRequestDTO programCreateRequestDTO, Integer th) {
        return Program.of(programCreateRequestDTO, th);
    }

    public ProgramIdResponseDTO toProgramIdResponseDTO(Long programId) {
        return ProgramIdResponseDTO.from(programId);
    }

    public ProgramTotalListDTO toProgramTotalListDTO(List<Program> programList) {
        return ProgramTotalListDTO.from(programList);
    }
}
