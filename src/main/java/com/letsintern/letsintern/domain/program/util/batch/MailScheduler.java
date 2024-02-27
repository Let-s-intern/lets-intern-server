package com.letsintern.letsintern.domain.program.util.batch;

import com.letsintern.letsintern.domain.program.domain.MailStatus;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MailScheduler {

    private final ProgramRepository programRepository;
    private final JobLauncher jobLauncher;
    private final RemindMailJobConfig remindMailJobConfig;
    private final ReviewMailJobConfig reviewMailJobConfig;

//    @Scheduled(cron = "0 10 0,15,18 * * ?")
//    public void sendRemindMail() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//        List<Program> mailStatusYetPrograms = programRepository.findAllLetsChatByMailStatusAndAnnouncementDate(MailStatus.YET, LocalDateTime.now());
//        for(Program program : mailStatusYetPrograms) {
//            jobLauncher.run(
//                    remindMailJobConfig.remindMailJob(),
//                    new JobParametersBuilder()
//                            .addLong("programId", program.getId())
//                            .addLong("time", new Date().getTime())
//                            .toJobParameters()
//            );
//        }
//    }

    @Scheduled(cron = "0 10 10 * * ?")
    public void sendReviewMail() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Program> mailStatusRemindPrograms = programRepository.findAllLetsChatByMailStatusAndEndDate(MailStatus.REMIND, LocalDateTime.now());
        for(Program program : mailStatusRemindPrograms) {
            jobLauncher.run(
                    reviewMailJobConfig.reviewMailJob(),
                    new JobParametersBuilder()
                            .addLong("programId", program.getId())
                            .addLong("time", new Date().getTime())
                            .toJobParameters()
            );
        }
    }

}
