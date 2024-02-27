package com.letsintern.letsintern.domain.application.repository;

import com.letsintern.letsintern.domain.application.domain.*;
import com.letsintern.letsintern.domain.application.dto.response.ApplicationChallengeAdminVoDetail;
import com.letsintern.letsintern.domain.application.filter.ApplicationFilter;
import com.letsintern.letsintern.domain.application.vo.ApplicationAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationChallengeAdminVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationEntireDashboardVo;
import com.letsintern.letsintern.domain.application.vo.ApplicationVo;
import com.letsintern.letsintern.domain.program.domain.ProgramFeeType;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.vo.UserProgramVo;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
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
                        qApplication.feeIsConfirmed,
                        qApplication.program.id,
                        qApplication.program.title,
                        qApplication.program.type,
                        qApplication.program.feeType,
                        qApplication.program.feeDueDate,
                        qApplication.program.accountType,
                        qApplication.program.accountNumber,
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
    public void updateAllApplicationByFeeDueDate(LocalDateTime now) {
        QApplication qApplication = QApplication.application;

        jpaQueryFactory
                .update(qApplication)
                .set(qApplication.status, ApplicationStatus.FEE_NOT_APPROVED)
                .where(
                        qApplication.program.feeType.ne(ProgramFeeType.FREE),
                        qApplication.program.status.eq(ProgramStatus.CLOSED),
                        qApplication.program.feeDueDate.isNotNull(),
                        qApplication.program.feeDueDate.before(now),
                        qApplication.isApproved.eq(true),
                        qApplication.feeIsConfirmed.eq(false))
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
    public List<String> findAllEmailByIsApprovedAndFeeIsConfirmed(Long programId, Boolean isApproved, Boolean feeIsConfirmed) {
        QApplication qApplication = QApplication.application;

        List<String> emailList = jpaQueryFactory
                .select(qApplication.user.email)
                .from(qApplication)
                .where(qApplication.program.id.eq(programId), qApplication.user.isNotNull(),
                        qApplication.isApproved.eq(isApproved), qApplication.feeIsConfirmed.eq(feeIsConfirmed))
                .fetch();

        emailList.addAll(jpaQueryFactory
                .select(qApplication.email)
                .from(qApplication)
                .where(qApplication.program.id.eq(programId), qApplication.user.isNull(),
                        qApplication.isApproved.eq(isApproved), qApplication.feeIsConfirmed.eq(feeIsConfirmed))
                .fetch());

        return emailList;
    }

    @Override
    public Page<ApplicationEntireDashboardVo> getEntireDashboardList(Long programId, ApplicationWishJob applicationWishJob, Long userId, Pageable pageable) {
        QApplication qApplication = QApplication.application;
        List<ApplicationEntireDashboardVo> applicationEntireDashboardVos;
        JPAQuery<Long> count;

        NumberExpression<Integer> isMineOrder = new CaseBuilder()
                .when(qApplication.user.id.eq(Expressions.asNumber(userId))).then(0)
                .otherwise(1);

        if(applicationWishJob == null || applicationWishJob.equals(ApplicationWishJob.ALL)) {
            applicationEntireDashboardVos = jpaQueryFactory
                    .select(Projections.constructor(ApplicationEntireDashboardVo.class,
                            qApplication.id,
                            qApplication.user.name,
                            qApplication.wishJob,
                            qApplication.introduction,
                            Expressions.asNumber(userId),
                            qApplication.user.id))
                    .from(qApplication)
                    .where(
                            qApplication.program.id.eq(programId),
                            qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE))
                    .orderBy(isMineOrder.asc(), qApplication.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = jpaQueryFactory
                    .select(qApplication.count())
                    .from(qApplication)
                    .where(
                            qApplication.program.id.eq(programId),
                            qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE));
        }

        else if(applicationWishJob.equals(ApplicationWishJob.MARKETING_ALL) || applicationWishJob.equals(ApplicationWishJob.DEVELOPMENT_ALL)) {
            applicationEntireDashboardVos = jpaQueryFactory
                    .select(Projections.constructor(ApplicationEntireDashboardVo.class,
                            qApplication.id,
                            qApplication.user.name,
                            qApplication.wishJob,
                            qApplication.introduction,
                            Expressions.asNumber(userId),
                            qApplication.user.id))
                    .from(qApplication)
                    .where(
                            qApplication.program.id.eq(programId),
                            qApplication.wishJob.in(ApplicationWishJob.getApplicationWishJobListByProgramTopic(applicationWishJob.getProgramTopic())),
                            qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE))
                    .orderBy(isMineOrder.asc(), qApplication.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = jpaQueryFactory
                    .select(qApplication.count())
                    .from(qApplication)
                    .where(
                            qApplication.program.id.eq(programId),
                            qApplication.program.topic.eq(applicationWishJob.getProgramTopic()),
                            qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE));
        }

        else {
            applicationEntireDashboardVos = jpaQueryFactory
                    .select(Projections.constructor(ApplicationEntireDashboardVo.class,
                            qApplication.id,
                            qApplication.user.name,
                            qApplication.wishJob,
                            qApplication.introduction,
                            Expressions.asNumber(userId),
                            qApplication.user.id))
                    .from(qApplication)
                    .where(
                            qApplication.program.id.eq(programId),
                            qApplication.wishJob.eq(applicationWishJob),
                            qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE))
                    .orderBy(isMineOrder.asc(), qApplication.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = jpaQueryFactory
                    .select(qApplication.count())
                    .from(qApplication)
                    .where(
                            qApplication.program.id.eq(programId),
                            qApplication.wishJob.eq(applicationWishJob),
                            qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE));
        }

        return PageableExecutionUtils.getPage(applicationEntireDashboardVos, pageable, count::fetchOne);
    }

    @Override
    public Page<ApplicationChallengeAdminVo> getApplicationChallengeAdminList(Long programId, Pageable pageable) {
        QApplication qApplication = QApplication.application;
        List<ApplicationChallengeAdminVo> applicationVos;
        JPAQuery<Long> count;

        applicationVos = jpaQueryFactory
                .select(Projections.constructor(ApplicationChallengeAdminVo.class,
                        qApplication.id,
                        qApplication.user.name,
                        qApplication.type,
                        qApplication.user.university,
                        qApplication.user.major,
                        qApplication.grade,
                        qApplication.user.email,
                        qApplication.user.phoneNum,
                        qApplication.inflowPath,
                        qApplication.user.accountType,
                        qApplication.user.accountNumber))
                .from(qApplication)
                .where(
                        qApplication.program.id.eq(programId),
                        qApplication.isApproved.eq(true),
                        qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE))
                .orderBy(qApplication.user.name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory
                .select(qApplication.count())
                .from(qApplication)
                .where(
                        qApplication.program.id.eq(programId),
                        qApplication.isApproved.eq(true),
                        qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE));

        return PageableExecutionUtils.getPage(applicationVos, pageable, count::fetchOne);
    }

    @Override
    public Page<ApplicationChallengeAdminVo> getApplicationChallengeAdminListFiltered(Long programId, Pageable pageable, String name, String email, String phoneNum) {
        QApplication qApplication = QApplication.application;
        List<ApplicationChallengeAdminVo> applicationVos;
        JPAQuery<Long> count;

        ApplicationFilter applicationFilter = ApplicationFilter.of(qApplication, name, email, phoneNum);

        applicationVos = jpaQueryFactory
                .select(Projections.constructor(ApplicationChallengeAdminVo.class,
                        qApplication.id,
                        qApplication.user.name,
                        qApplication.type,
                        qApplication.user.university,
                        qApplication.user.major,
                        qApplication.grade,
                        qApplication.user.email,
                        qApplication.user.phoneNum,
                        qApplication.inflowPath,
                        qApplication.user.accountType,
                        qApplication.user.accountNumber))
                .from(qApplication)
                .where(
                        qApplication.program.id.eq(programId),
                        qApplication.isApproved.eq(true),
                        qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE),
                        applicationFilter.eqName(), applicationFilter.eqEmail(), applicationFilter.eqPhoneNum())
                .orderBy(qApplication.user.name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory
                .select(qApplication.count())
                .from(qApplication)
                .where(
                        qApplication.program.id.eq(programId),
                        qApplication.isApproved.eq(true),
                        qApplication.status.in(ApplicationStatus.IN_PROGRESS, ApplicationStatus.DONE));

        return PageableExecutionUtils.getPage(applicationVos, pageable, count::fetchOne);
    }
}
