package com.letsintern.letsintern.domain.program.util;

import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProgramScheduler {

    private final ProgramRepository programRepository;

    @Transactional
    @Scheduled(cron = "5 0 0,15,18 * * ?")
    public void updateProgramStatusToClosed() {
        programRepository.updateAllByDueDate(LocalDateTime.now());
    }

    @Transactional
    @Scheduled(cron = "45 0 11-23 * * ?")
    public void updateProgramStatusToDone() {
        programRepository.updateAllByEndDate(LocalDateTime.now());
    }
}
