package com.letsintern.letsintern.domain.program.util.batch;

import com.letsintern.letsintern.domain.program.domain.MailStatus;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.repository.ProgramRepository;
import com.letsintern.letsintern.domain.program.util.batch.review.ReviewMailJobConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MailScheduler {

    private final ProgramRepository programRepository;
    private final JobLauncher jobLauncher;
    private final ReviewMailJobConfig reviewMailJobConfig;

    @Scheduled(fixedRate = 1000000)
    public void sendReviewMail() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        List<Program> mailStatusYetPrograms = programRepository.findAllLetsChatByMailStatus(MailStatus.YET);
        for(Program program : mailStatusYetPrograms) {
            jobLauncher.run(
                    reviewMailJobConfig.reviewMailJob(),
                    new JobParametersBuilder()
                            .addLong("programIdStr", program.getId())
                            .toJobParameters()
            );
        }

    }
}
