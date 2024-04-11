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
public class LetsChatReviewMailJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final EmailUtils emailUtils;
    private final ApplicationHelper applicationHelper;
    private final LetsChatHelper letsChatHelper;

    @Bean
    public Job reviewMailJob() {
        return new JobBuilder("reviewMailJob", jobRepository)
                .start(reviewMailStep())
                .next(updateMailStatusReviewStep())
                .preventRestart()
                .build();
    }

    @Bean
    public Step reviewMailStep() {
        return new StepBuilder("reviewMailStep", jobRepository)
                .tasklet(sendReviewMailTasklet(null), transactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet sendReviewMailTasklet(@Value("#{jobParameters[programId]}") Long programId) {
        return ((contribution, chunkContext) -> {
            LetsChat letsChat = letsChatHelper.findLetsChatByIdOrThrow(programId);
            List<String> applicationEmailList = applicationHelper.getApplicationEmailListByPaymentFeeType(letsChat.getPayment().getFeeType(), letsChat.getId());
            if(applicationEmailList != null && !applicationEmailList.isEmpty()) emailUtils.sendLetsChatReviewMail(applicationEmailList, letsChat);
            return RepeatStatus.FINISHED;
        });
    }

    @Bean
    public Step updateMailStatusReviewStep() {
        return new StepBuilder("updateMailStatusReviewStep", jobRepository)
                .tasklet(updateMailStatusReviewTasklet(null), transactionManager)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet updateMailStatusReviewTasklet(@Value("#{jobParameters[programId]}") Long programId) {
        return ((contribution, chunkContext) -> {
            LetsChat letsChat = letsChatHelper.findLetsChatByIdOrThrow(programId);
            letsChat.updateLetsChatMailStatus(MailStatus.REVIEW);
            letsChatHelper.saveLetsChat(letsChat);
            return RepeatStatus.FINISHED;
        });
    }

}
