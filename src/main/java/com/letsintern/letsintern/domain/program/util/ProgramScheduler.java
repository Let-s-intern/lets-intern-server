package com.letsintern.letsintern.domain.program.util;

import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class ProgramScheduler {

    private final ProgramRepository programRepository;

    @Transactional
    @Scheduled(cron = "5 19 0,15,18,21 * * ?")
    public void updateProgramStatusToClosed() {
        programRepository.updateAllByDueDate(LocalDateTime.now());
    }

    @Transactional
    @Scheduled(cron = "45 19 11-23 * * ?")
    public void updateProgramStatusToDone() {
        programRepository.updateAllByEndDate(LocalDateTime.now());
    }
}
