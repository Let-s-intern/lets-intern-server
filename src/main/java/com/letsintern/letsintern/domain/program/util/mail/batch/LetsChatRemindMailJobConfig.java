package com.letsintern.letsintern.domain.program.util.mail.batch;

import com.letsintern.letsintern.domain.application.helper.ApplicationHelper;
import com.letsintern.letsintern.domain.program.domain.LetsChat;
import com.letsintern.letsintern.domain.program.domain.MailStatus;
import com.letsintern.letsintern.domain.program.helper.LetsChatHelper;
import com.letsintern.letsintern.global.common.util.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class LetsChatRemindMailJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final EmailUtils emailUtils;
    private final ApplicationHelper applicationHelper;
    private final LetsChatHelper letsChatHelper;

    @Bean
    public Job remindMailJob() {
        return new JobBuilder("remindMailJob", jobRepository)
                .start(remindMailStep())
                .next(updateMailStatusRemindStep())
                .preventRestart()
                .build();
    }

    @Bean
    public Step remindMailStep() {
        return new StepBuilder("remindMailStep", jobRepository)
                .tasklet(sendRemindMailTasklet(null), transactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet sendRemindMailTasklet(@Value("#{jobParameters[programId]}") Long programId) {
        return ((contribution, chunkContext) -> {
            LetsChat letsChat = letsChatHelper.findLetsChatByIdOrThrow(programId);
            List<String> applicationEmailList = applicationHelper.getApplicationEmailListByPaymentFeeType(letsChat.getPayment().getFeeType(), letsChat.getId());
            if(applicationEmailList != null && !applicationEmailList.isEmpty()) emailUtils.sendLetsChatRemindMail(applicationEmailList, letsChat);
            return RepeatStatus.FINISHED;
        });
    }

    @Bean
    public Step updateMailStatusRemindStep() {
        return new StepBuilder("updateMailStatusRemindStep", jobRepository)
                .tasklet(updateMailStatusRemindTasklet(null), transactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet updateMailStatusRemindTasklet(@Value("#{jobParameters[programId]}") Long programId) {
        return ((contribution, chunkContext) -> {
            LetsChat letsChat = letsChatHelper.findLetsChatByIdOrThrow(programId);
            letsChat.updateLetsChatMailStatus(MailStatus.REMIND);
            letsChatHelper.saveLetsChat(letsChat);
            return RepeatStatus.FINISHED;
        });
    }

}
