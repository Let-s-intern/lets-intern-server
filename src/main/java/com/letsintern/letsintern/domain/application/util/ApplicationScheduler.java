package com.letsintern.letsintern.domain.application.util;

import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ApplicationScheduler {

    private final ApplicationRepository applicationRepository;

    @Transactional
    @Scheduled(cron = "25 0 0,15,18 * * ?")
    public void updateApplicationStatusToNotApproved() {
        applicationRepository.updateAllApplicationByAnnouncementDate(LocalDateTime.now());
    }

    @Transactional
    @Scheduled(cron = "0 1 0,15,18 * * ?")
    public void updateApplicationStatusToFeeNotApproved() {
        applicationRepository.updateAllApplicationByFeeDueDate(LocalDateTime.now());
    }
}
