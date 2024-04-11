package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.dto.request.ProgramRequestDto;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.vo.program.ProgramDetailVo;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ProgramHelper {
    private final ProgramRepository programRepository;

    public String parseToFaqIdList(List<Long> faqIdList) {
        if (Objects.isNull(faqIdList))
            return null;
        return StringUtils.listToString(faqIdList);
    }

    public ProgramStatus getProgramStatusForDueDate(ProgramRequestDto programRequestDto) {
        if (Objects.isNull(programRequestDto.dueDate()))
            return null;
        if (programRequestDto.dueDate().isAfter(LocalDateTime.now())) {
            return ProgramStatus.OPEN;
        } else {
            return ProgramStatus.CLOSED;
        }
    }

    public ProgramDetailVo findProgramDetailOrThrow(Long programId) {
        return programRepository.findProgramDetailVo(programId)
                .orElseThrow(() -> ProgramNotFound.EXCEPTION);
    }

    public Program findProgramOrThrow(Long programId) {
        return programRepository.findById(programId)
                .orElseThrow(() -> ProgramNotFound.EXCEPTION);
    }

    public void saveProgram(Program program) {
        programRepository.save(program);
    }
}
