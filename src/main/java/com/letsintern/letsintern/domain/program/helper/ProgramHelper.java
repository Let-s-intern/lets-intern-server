package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.faq.repository.FaqRepository;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.dto.request.ProgramRequestDto;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.mapper.ProgramMapper;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.vo.program.ProgramDetailVo;
import com.letsintern.letsintern.domain.review.repository.ReviewRepository;
import com.letsintern.letsintern.global.common.util.EmailUtils;
import com.letsintern.letsintern.global.common.util.StringUtils;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ProgramHelper {
    private final static int RANDOM_NUMBER_LENGTH = 4;
    private final ProgramRepository programRepository;

    public String parseToFaqIdList(List<Long> faqIdList) {
        return StringUtils.listToString(faqIdList);
    }

    public ProgramStatus getProgramStatusForDueDate(ProgramRequestDto programRequestDto) {
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
