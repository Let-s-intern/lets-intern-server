package com.letsintern.letsintern.domain.program.util.mail;

import com.letsintern.letsintern.domain.program.domain.LetsChat;
import com.letsintern.letsintern.domain.program.repository.LetsChatRepository;
import com.letsintern.letsintern.domain.program.util.mail.batch.LetsChatRemindMailJobConfig;
import com.letsintern.letsintern.domain.program.util.mail.batch.LetsChatReviewMailJobConfig;
import com.letsintern.letsintern.domain.program.domain.MailStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MailScheduler {

    private final LetsChatRepository letsChatRepository;
    private final JobLauncher jobLauncher;
    private final LetsChatRemindMailJobConfig letsChatRemindMailJobConfig;
    private final LetsChatReviewMailJobConfig letsChatReviewMailJobConfig;

    @Scheduled(cron = "0 1 9 * * ?")
    public void sendLetsChatRemindMail() {
        List<LetsChat> mailStatusYetLetsChats = letsChatRepository.findAllLetsChatByMailStatusAndStartDate(MailStatus.YET, LocalDate.now());
        mailStatusYetLetsChats.stream()
                .map(LetsChat::getId)
                .forEach(id -> {
                    try {
                        jobLauncher.run(
                                letsChatRemindMailJobConfig.remindMailJob(),
                                new JobParametersBuilder()
                                        .addLong("programId", id)
                                        .addLong("time", new Date().getTime())
                                        .toJobParameters()
                        );
                    } catch (JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException |
                             JobParametersInvalidException | JobRestartException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @Scheduled(cron = "0 1 23 * * ?")
    public void sendLetsChatReviewMail() {
        List<LetsChat> mailStatusRemindLetsChats = letsChatRepository.findAllLetsChatByMailStatusAndEndDate(MailStatus.REMIND, LocalDateTime.now());
        mailStatusRemindLetsChats.stream()
                .map(LetsChat::getId)
                .forEach(id -> {
                    try {
                        jobLauncher.run(
                                letsChatReviewMailJobConfig.reviewMailJob(),
                                new JobParametersBuilder()
                                        .addLong("programId", id)
                                        .addLong("time", new Date().getTime())
                                        .toJobParameters()
                        );
                    } catch (JobExecutionAlreadyRunningException | JobRestartException |
                             JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}
