package com.letsintern.letsintern.domain.program.util.mail;

import com.letsintern.letsintern.domain.program.domain.MailStatus;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.util.mail.batch.LetsChatRemindMailJobConfig;
import com.letsintern.letsintern.domain.program.util.mail.batch.LetsChatReviewMailJobConfig;
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

    private final ProgramRepository programRepository;
    private final JobLauncher jobLauncher;
    private final LetsChatRemindMailJobConfig letsChatRemindMailJobConfig;
    private final LetsChatReviewMailJobConfig letsChatReviewMailJobConfig;

    @Scheduled(cron = "0 1 9 * * ?")
    public void sendLetsChatRemindMail() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Program> mailStatusYetLetsChats = programRepository.findAllLetsChatByMailStatusAndStartDate(MailStatus.YET, LocalDate.now());
        for(Program letsChat : mailStatusYetLetsChats) {
            jobLauncher.run(
                    letsChatRemindMailJobConfig.remindMailJob(),
                    new JobParametersBuilder()
                            .addLong("programId", letsChat.getId())
                            .addLong("time", new Date().getTime())
                            .toJobParameters()
            );
        }
    }

    @Scheduled(cron = "0 1 23 * * ?")
    public void sendLetsChatReviewMail() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Program> mailStatusRemindLetsChats = programRepository.findAllLetsChatByMailStatusAndEndDate(MailStatus.REMIND, LocalDateTime.now());
        for(Program letsChat : mailStatusRemindLetsChats) {
            jobLauncher.run(
                    letsChatReviewMailJobConfig.reviewMailJob(),
                    new JobParametersBuilder()
                            .addLong("programId", letsChat.getId())
                            .addLong("time", new Date().getTime())
                            .toJobParameters()
            );
        }
    }

}
