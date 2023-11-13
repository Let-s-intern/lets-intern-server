package com.letsintern.letsintern.domain.program.mapper;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramIdResponseDTO;
import com.letsintern.letsintern.domain.program.dto.response.ProgramListDTO;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgramMapper {

    public Program toEntity(ProgramCreateRequestDTO programCreateRequestDTO) {
        return Program.of(programCreateRequestDTO);
    }

    public ProgramIdResponseDTO toProgramIdResponseDTO(Long programId) {
        return ProgramIdResponseDTO.from(programId);
    }

    public ProgramListDTO toProgramListDTO(List<ProgramThumbnailVo> openProgramList, List<ProgramThumbnailVo> closedProgramList) {
        return ProgramListDTO.from(openProgramList, closedProgramList);
    }
}
