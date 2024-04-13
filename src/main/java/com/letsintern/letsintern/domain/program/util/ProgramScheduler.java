package com.letsintern.letsintern.domain.program.util;

import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgramScheduler {

    private final ProgramRepository programRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    @Scheduled(cron = "5 0 0,15,18 * * ?")
    public void updateProgramStatus() {
        programRepository.updateAllProgramStatusClosedByDueDate(LocalDateTime.now());
    }

    @Transactional
    @Scheduled(cron = "45 0 11-23 * * ?")
    public void updateProgramStatusToDone() {
        List<Long> programIdList = programRepository.findAllProgramIdListAndUpdateStatusToDone(LocalDateTime.now());
        for(Long programId : programIdList) {
            applicationRepository.updateAllApplicationStatusDone(programId);
        }
    }
}
