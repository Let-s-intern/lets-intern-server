package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.*;
import com.letsintern.letsintern.domain.application.vo.UserApplicationVo;
import com.querydsl.core.types.Projections;
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
    public List<Application> findAllByProgramIdAndIsApproved(Long programId, Boolean isApproved, Pageable pageable) {
        QApplication qApplication = QApplication.application;

        return jpaQueryFactory
                .select(qApplication)
                .from(qApplication)
                .where(qApplication.program.id.eq(programId), qApplication.isApproved.eq(isApproved))
                .orderBy(qApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<UserApplicationVo> findAllByUserId(Long userId, Pageable pageable) {
        QUserApplication qUserApplication = QUserApplication.userApplication;

        return jpaQueryFactory
                .select(Projections.constructor(UserApplicationVo.class,
                        qUserApplication.id,
                        qUserApplication.reviewId,
                        qUserApplication.isApproved,
                        qUserApplication.program.title,
                        qUserApplication.program.status
                ))
                .from(qUserApplication)
                .where(qUserApplication.user.id.eq(userId))
                .orderBy(qUserApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public UserApplication findByProgramIdAndUserId(Long programId, Long userId) {
        QUserApplication qUserApplication = QUserApplication.userApplication;

        return jpaQueryFactory
                .select(qUserApplication)
                .from(qUserApplication)
                .where(qUserApplication.program.id.eq(programId), qUserApplication.user.id.eq(userId))
                .fetchFirst();
    }

    @Override
    public GuestApplication findByProgramIdAndGuestEmail(Long programId, String email) {
        QGuestApplication qGuestApplication = QGuestApplication.guestApplication;

        return jpaQueryFactory
                .selectFrom(qGuestApplication)
                .where(qGuestApplication.program.id.eq(programId), qGuestApplication.guestEmail.eq(email))
                .fetchFirst();
    }
}
