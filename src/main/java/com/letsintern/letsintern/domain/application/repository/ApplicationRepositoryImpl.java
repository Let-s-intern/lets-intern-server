package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.*;
import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationVo;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    @Override
    public List<ApplicationAdminVo> findAllByProgramId(Long programId, Pageable pageable) {
        QApplication qApplication = QApplication.application;

        return jpaQueryFactory
                .select(Projections.constructor(ApplicationAdminVo.class,
                        qApplication
                ))
                .where(qApplication.program.id.eq(programId))
                .from(qApplication)
                .orderBy(qApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<ApplicationAdminVo> findAllByProgramIdAndIsApproved(Long programId, Boolean isApproved, Pageable pageable) {
        QApplication qApplication = QApplication.application;

        return jpaQueryFactory
                .select(Projections.constructor(ApplicationAdminVo.class,
                        qApplication
                ))
                .from(qApplication)
                .where(qApplication.program.id.eq(programId), qApplication.isApproved.eq(isApproved))
                .orderBy(qApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<ApplicationVo> findAllByUserId(Long userId, Pageable pageable) {
        QApplication qApplication = QApplication.application;

        return jpaQueryFactory
                .select(Projections.constructor(ApplicationVo.class,
                        qApplication.id,
                        qApplication.status,
                        qApplication.program.id,
                        qApplication.program.title,
                        qApplication.program.type,
                        qApplication.reviewId
                ))
                .from(qApplication)
                .where(qApplication.user.id.eq(userId))
                .orderBy(qApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Application> findAllByUserId(Long userId) {
        QApplication qApplication = QApplication.application;
        
        return jpaQueryFactory
                .selectFrom(qApplication)
                .where(qApplication.user.id.eq(userId))
                .orderBy(qApplication.id.desc())
                .fetch();
    }

    @Override
    public List<Program> findAllProgramByUserId(Long userId) {
        QApplication qApplication = QApplication.application;

        return jpaQueryFactory
                .select(qApplication.program)
                .from(qApplication)
                .where(qApplication.user.id.eq(userId), qApplication.isApproved.eq(true))
                .orderBy(qApplication.id.desc())
                .fetch();
    }

    @Override
    public Application findByProgramIdAndUserId(Long programId, Long userId) {
        QApplication qApplication = QApplication.application;

        return jpaQueryFactory
                .select(qApplication)
                .from(qApplication)
                .where(qApplication.program.id.eq(programId), qApplication.user.id.eq(userId))
                .fetchFirst();
    }

    @Override
    public Application findByProgramIdAndGuestEmail(Long programId, String email) {
        QApplication qApplication = QApplication.application;

        return jpaQueryFactory
                .selectFrom(qApplication)
                .where(qApplication.program.id.eq(programId), qApplication.email.eq(email))
                .fetchFirst();
    }

    @Override
    public void updateAllStatusByProgramId(Long programId) {
        QApplication qApplication = QApplication.application;

        jpaQueryFactory
                .update(qApplication)
                .set(qApplication.status, ApplicationStatus.APPLIED_NOT_APPROVED)
                .where(qApplication.program.id.eq(programId), qApplication.isApproved.eq(false))
                .execute();

        em.flush();
        em.clear();
    }

    @Override
    public List<String> findAllEmailByIsApproved(Long programId, Boolean isApproved) {
        QApplication qApplication = QApplication.application;

        List<String> emailList = jpaQueryFactory
                .select(qApplication.user.email)
                .from(qApplication)
                .where(qApplication.program.id.eq(programId), qApplication.user.isNotNull(), qApplication.isApproved.eq(isApproved))
                .fetch();

        emailList.addAll(jpaQueryFactory
                .select(qApplication.email)
                .from(qApplication)
                .where(qApplication.program.id.eq(programId), qApplication.user.isNull(), qApplication.isApproved.eq(isApproved))
                .fetch());

        return emailList;
    }
}
