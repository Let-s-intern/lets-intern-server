package com.letsintern.letsintern.domain.program.util.batch.review;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.program.domain.Program;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ReviewMailJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job reviewMailJob() {
        return new JobBuilder("reviewMailJob", jobRepository)
                .start(reviewMailStep())
                .preventRestart()
                .build();
    }

    @Bean
    public Step reviewMailStep() {
        return new StepBuilder("reviewMailStep", jobRepository)
                .<Application, Program>chunk(20, transactionManager)
                .reader(reviewMailItemReader(null))
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Application> reviewMailItemReader(@Value("#{jobParameters[programId]}") Long programId) {
        return new JpaPagingItemReaderBuilder<Application>()
                .name("reviewMailItemReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(10)
                .queryString("SELECT a FROM Application a where a.program_id = " + programId)
                .build();
    }
}
