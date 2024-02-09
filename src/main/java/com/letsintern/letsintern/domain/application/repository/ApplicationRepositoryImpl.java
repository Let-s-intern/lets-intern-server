package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.*;
import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationEntireDashboardVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationVo;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.vo.UserProgramVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements ApplicationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    @Override
    public Page<ApplicationAdminVo> findAllByProgramId(Long programId, Pageable pageable) {
        QApplication qApplication = QApplication.application;
        List<ApplicationAdminVo> applicationAdminVos;
        JPAQuery<Long> count;

        applicationAdminVos = jpaQueryFactory
                .select(Projections.constructor(ApplicationAdminVo.class,
                        qApplication
                ))
                .from(qApplication)
                .where(qApplication.program.id.eq(programId))
                .orderBy(qApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qApplication.count())
                .from(qApplication)
                .where(qApplication.program.id.eq(programId))
                .from(qApplication);

        return PageableExecutionUtils.getPage(applicationAdminVos, pageable, count::fetchOne);
    }

    @Override
    public Page<ApplicationAdminVo> findAllByProgramIdAndIsApproved(Long programId, Boolean isApproved, Pageable pageable) {
        QApplication qApplication = QApplication.application;
        List<ApplicationAdminVo> applicationAdminVos;
        JPAQuery<Long> count;

        applicationAdminVos = jpaQueryFactory
                .select(Projections.constructor(ApplicationAdminVo.class,
                        qApplication
                ))
                .from(qApplication)
                .where(qApplication.program.id.eq(programId), qApplication.isApproved.eq(isApproved))
                .orderBy(qApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qApplication.count())
                .from(qApplication)
                .where(qApplication.program.id.eq(programId), qApplication.isApproved.eq(isApproved))
                .from(qApplication);

        return PageableExecutionUtils.getPage(applicationAdminVos, pageable, count::fetchOne);
    }

    @Override
    public Page<ApplicationVo> findAllByUserId(Long userId, Pageable pageable) {
        QApplication qApplication = QApplication.application;
        List<ApplicationVo> applicationVos;
        JPAQuery<Long> count;

        applicationVos = jpaQueryFactory
                .select(Projections.constructor(ApplicationVo.class,
                        qApplication.id,
                        qApplication.status,
                        qApplication.program.id,
                        qApplication.program.title,
                        qApplication.program.type,
                        qApplication.reviewId,
                        qApplication.program.announcementDate,
                        qApplication.program.startDate,
                        qApplication.program.endDate
                ))
                .from(qApplication)
                .where(qApplication.user.id.eq(userId))
                .orderBy(qApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qApplication.count())
                .from(qApplication)
                .where(qApplication.user.id.eq(userId));

        return PageableExecutionUtils.getPage(applicationVos, pageable, count::fetchOne);
    }

    @Override
    public Page<Application> findAllByUserIdAdmin(Long userId, Pageable pageable) {
        QApplication qApplication = QApplication.application;
        List<Application> applicationList;
        JPAQuery<Long> count;

        applicationList = jpaQueryFactory
                .selectFrom(qApplication)
                .where(qApplication.user.id.eq(userId))
                .orderBy(qApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qApplication.count())
                .from(qApplication)
                .where(qApplication.user.id.eq(userId));

        return PageableExecutionUtils.getPage(applicationList, pageable, count::fetchOne);
    }

    @Override
    public Page<UserProgramVo> findAllProgramByUserId(Long userId, Pageable pageable) {
        QApplication qApplication = QApplication.application;
        List<UserProgramVo> userProgramVos;
        JPAQuery<Long> count;

        userProgramVos = jpaQueryFactory
                .select(Projections.constructor(UserProgramVo.class,
                        qApplication.program.id,
                        qApplication.program.type,
                        qApplication.program.th,
                        qApplication.program.title))
                .from(qApplication)
                .where(qApplication.user.id.eq(userId), qApplication.isApproved.eq(true))
                .orderBy(qApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qApplication.count())
                .from(qApplication)
                .where(qApplication.user.id.eq(userId), qApplication.isApproved.eq(true));

        return PageableExecutionUtils.getPage(userProgramVos, pageable, count::fetchOne);
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
    public void updateAllApplicationByAnnouncementDate(LocalDateTime now) {
        QApplication qApplication = QApplication.application;

        jpaQueryFactory
                .update(qApplication)
                .set(qApplication.status, ApplicationStatus.APPLIED_NOT_APPROVED)
                .where(
                        qApplication.program.status.eq(ProgramStatus.CLOSED),
                        qApplication.program.announcementDate.before(now),
                        qApplication.status.eq(ApplicationStatus.APPLIED),
                        qApplication.isApproved.eq(false))
                .execute();

        em.flush();
        em.clear();
    }

    @Override
    public void updateAllApplicationStatusDone(Long programId) {
        QApplication qApplication = QApplication.application;

        jpaQueryFactory
                .update(qApplication)
                .set(qApplication.status, ApplicationStatus.DONE)
                .where(
                        qApplication.program.id.eq(programId),
                        qApplication.status.eq(ApplicationStatus.IN_PROGRESS))
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

    @Override
    public Optional<ApplicationEntireDashboardVo> getEntireDashboardMyVo(Long programId, Long userId) {
        QApplication qApplication = QApplication.application;

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(ApplicationEntireDashboardVo.class,
                        qApplication.id,
                        qApplication.user.name,
                        qApplication.wishJob,
                        qApplication.introduction))
                .from(qApplication)
                .where(
                        qApplication.program.id.eq(programId),
                        qApplication.user.id.eq(userId),
                        qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE))
                .fetchFirst());
    }

    @Override
    public Page<ApplicationEntireDashboardVo> getEntireDashboardList(Long programId, Long userId, Pageable pageable) {
        QApplication qApplication = QApplication.application;
        List<ApplicationEntireDashboardVo> applicationEntireDashboardVos;
        JPAQuery<Long> count;

        applicationEntireDashboardVos = jpaQueryFactory
                .select(Projections.constructor(ApplicationEntireDashboardVo.class,
                        qApplication.id,
                        qApplication.user.name,
                        qApplication.wishJob,
                        qApplication.introduction))
                .from(qApplication)
                .where(
                        qApplication.program.id.eq(programId),
                        qApplication.user.id.ne(userId),
                        qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE))
                .orderBy(qApplication.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory
                .select(qApplication.count())
                .from(qApplication)
                .where(
                        qApplication.program.id.eq(programId),
                        qApplication.user.id.ne(userId),
                        qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE));

        return PageableExecutionUtils.getPage(applicationEntireDashboardVos, pageable, count::fetchOne);
    }
}
