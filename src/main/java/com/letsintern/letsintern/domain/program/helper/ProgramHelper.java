package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgramHelper {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    private Integer findMaxTh(ProgramType type) {
        return programRepository.maxTh(type);
    }

    public Long createProgram(ProgramCreateRequestDTO programCreateRequestDTO) {
        Program newProgram = programMapper.toEntity(programCreateRequestDTO, findMaxTh(programCreateRequestDTO.getType()) + 1);
        Program savedProgram = programRepository.save(newProgram);

        return savedProgram.getId();
    }

    public List<Program> getProgramTotalList() {
        return programRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
