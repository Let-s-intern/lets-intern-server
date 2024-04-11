package com.letsintern.letsintern.domain.program.helper;

import com.letsintern.letsintern.domain.program.domain.MailType;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.dto.request.ProgramRequestDto;
import com.letsintern.letsintern.domain.program.exception.ProgramNotFound;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.vo.program.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.program.UserProgramVo;
import com.letsintern.letsintern.global.common.util.EmailUtils;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ProgramHelper {
    private final ProgramRepository programRepository;
    private final EmailUtils emailUtils;

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

    public String createChallengeProgramEmailByMailType(Program program, MailType mailType) {
        if (MailType.APPROVED.equals(mailType))
            return emailUtils.getChallengeApprovedEmailText(program);
        else if (MailType.FEE_CONFIRMED.equals(mailType))
            return emailUtils.getChallengeFeeConfirmedEmailText(program);
        else
            return null;
    }

    public ProgramDetailVo findProgramDetailOrThrow(Long programId) {
        return programRepository.findProgramDetailVo(programId)
                .orElseThrow(() -> ProgramNotFound.EXCEPTION);
    }

    public Program findProgramOrThrow(Long programId) {
        return programRepository.findById(programId)
                .orElseThrow(() -> ProgramNotFound.EXCEPTION);
    }

    public Long findCountForProgramStatus(ProgramStatus status) {
        return programRepository.countByStatusEquals(status);
    }

    public void saveProgram(Program program) {
        programRepository.save(program);
    }
}
