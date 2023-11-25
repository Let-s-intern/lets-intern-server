package com.letsintern.letsintern.domain.program.util;

import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class ProgramScheduler {

    private final ProgramRepository programRepository;

    @Transactional
    @Scheduled(cron = "5 0 0 * * ?")
    public void updateProgramStatus() {
        programRepository.updateAllByDueDate(new Date());
    }
}
