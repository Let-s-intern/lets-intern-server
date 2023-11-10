package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.QApplication;
import com.letsintern.letsintern.domain.application.domain.QUserApplication;
import com.letsintern.letsintern.domain.application.domain.UserApplication;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Application> findAllByProgramId(Long programId, Pageable pageable) {
        QApplication qApplication = QApplication.application;

        return jpaQueryFactory
                .selectFrom(qApplication)
                .where(qApplication.program.id.eq(programId))
                .orderBy(qApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Application> findAllByProgramIdAndApproved(Long programId, Boolean approved, Pageable pageable) {
        QApplication qApplication = QApplication.application;

        return jpaQueryFactory
                .select(qApplication)
                .from(qApplication)
                .where(qApplication.program.id.eq(programId), qApplication.approved.eq(approved))
                .orderBy(qApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<UserApplication> findAllByUserId(Long userId, Pageable pageable) {
        QUserApplication qUserApplication = QUserApplication.userApplication;

        return jpaQueryFactory
                .select(qUserApplication)
                .from(qUserApplication)
                .where(qUserApplication.user.id.eq(userId))
                .orderBy(qUserApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
