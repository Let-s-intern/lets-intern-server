package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.QApplication;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Application> findAllByProgramId(Long programId) {
        QApplication qApplication = QApplication.application;

        return jpaQueryFactory
                .select(qApplication)
                .from(qApplication)
                .where(qApplication.program.id.eq(programId))
                .fetch();
    }

    @Override
    public List<Application> findAllByUserId(Long userId) {
        QApplication qApplication = QApplication.application;

        return jpaQueryFactory
                .select(qApplication)
                .from(qApplication)
                .where(qApplication.user.id.eq(userId))
                .fetch();
    }
}
