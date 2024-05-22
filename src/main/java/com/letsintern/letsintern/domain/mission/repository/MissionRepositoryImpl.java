package com.letsintern.letsintern.domain.mission.repository;

import com.letsintern.letsintern.domain.contents.domain.QContents;
import com.letsintern.letsintern.domain.mission.vo.*;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.letsintern.letsintern.domain.attendance.domain.QAttendance.attendance;
import static com.letsintern.letsintern.domain.contents.domain.QContents.contents;
import static com.letsintern.letsintern.domain.mission.domain.QMission.mission;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<MissionAdminVo> getMissionAdminList(Long programId, Pageable pageable) {
        List<MissionAdminVo> missionAdminVos;
        JPAQuery<Long> count;

        missionAdminVos = jpaQueryFactory
                .select(Projections.constructor(MissionAdminVo.class,
                        mission.id,
                        mission.th,
                        mission.type,
                        mission.refund,
                        mission.title,
                        mission.startDate,
                        mission.endDate,
                        mission.status,
                        contents.topic,
                        mission.attendanceCount,
                        mission.lateAttendanceCount,
                        mission.program.finalHeadCount))
                .from(mission)
                .leftJoin(contents)
                .on(
                        mission.essentialContentsId.eq(contents.id)
                )
                .where(mission.program.id.eq(programId))
                .orderBy(mission.th.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(mission.count())
                .from(mission)
                .where(mission.program.id.eq(programId));

        return PageableExecutionUtils.getPage(missionAdminVos, pageable, count::fetchOne);
    }

    @Override
    public Optional<MissionAdminDetailVo> getMissionAdminDetailVo(Long missionId) {
        QContents qEssentialContents = new QContents("essential");
        QContents qAdditionalContents = new QContents("additional");
        QContents qLimitedContents = new QContents("limited");

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(MissionAdminDetailVo.class,
                        mission.id,
                        mission.th,
                        mission.type,
                        mission.topic,
                        mission.status,
                        mission.title,
                        mission.contents,
                        mission.guide,
                        mission.template,
                        mission.comments,
                        mission.startDate,
                        mission.endDate,
                        mission.refund,
                        mission.program.feeRefund,
                        qEssentialContents.topic,
                        qAdditionalContents.topic,
                        qLimitedContents.topic))
                .from(mission)
                        .leftJoin(qEssentialContents)
                        .on(mission.essentialContentsId.eq(qEssentialContents.id))
                        .leftJoin(qAdditionalContents)
                        .on(mission.additionalContentsId.eq(qAdditionalContents.id))
                        .leftJoin(qLimitedContents)
                        .on(mission.limitedContentsId.eq(qLimitedContents.id))
                .where(mission.id.eq(missionId))
                .fetchFirst());
    }

    @Override
    public List<MissionAdminSimpleVo> getMissionAdminSimpleList(Long programId) {
        return jpaQueryFactory
                .select(Projections.constructor(MissionAdminSimpleVo.class,
                        mission.id,
                        mission.th,
                        mission.startDate,
                        mission.endDate,
                        mission.attendanceCount,
                        mission.lateAttendanceCount,
                        mission.status))
                .from(mission)
                .where(mission.program.id.eq(programId))
                .orderBy(mission.th.asc())
                .fetch();
    }

    @Override
    public List<MissionDashboardListVo> getMissionDashboardList(Long programId, Long userId) {
        return jpaQueryFactory
                .select(Projections.constructor(MissionDashboardListVo.class,
                        mission.id,
                        mission.th,
                        mission.topic,
                        mission.type,
                        mission.startDate,
                        mission.endDate,
                        mission.comments,
                        mission.refund,
                        attendance))
                .from(mission)
                .leftJoin(attendance)
                .on(
                        attendance.mission.eq(mission),
                        attendance.user.id.eq(userId)
                )
                .where(mission.program.id.eq(programId))
                .orderBy(mission.th.asc())
                .fetch();
    }

    @Override
    public List<MissionMyDashboardListVo> getMissionMyDashboardList(Long programId, Long userId) {
        return jpaQueryFactory
                .select(Projections.constructor(MissionMyDashboardListVo.class,
                        mission.id,
                        mission.th,
                        mission.title,
                        mission.status,
                        mission.type,
                        attendance))
                .from(mission)
                .leftJoin(attendance)
                .on(
                        attendance.mission.eq(mission),
                        attendance.user.id.eq(userId)
                )
                .where(mission.program.id.eq(programId))
                .orderBy(mission.th.desc())
                .fetch();
    }

    @Override
    public Optional<MissionMyDashboardDoneVo> getMissionMyDashboardDoneVo(Long missionId, Long userId) {
        QContents qEssentialContents = new QContents("essential");
        QContents qAdditionalContents = new QContents("additional");
        QContents qLimitedContents = new QContents("limited");

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(MissionMyDashboardDoneVo.class,
                        mission.id,
                        mission.th,
                        mission.type,
                        mission.title,
                        mission.contents,
                        qEssentialContents.link,
                        qAdditionalContents.link,
                        qLimitedContents.link,
                        mission.comments,
                        attendance))
                .from(mission)
                .leftJoin(attendance)
                .on(
                        attendance.mission.eq(mission),
                        attendance.user.id.eq(userId)
                )
                .leftJoin(qEssentialContents)
                .on(mission.essentialContentsId.eq(qEssentialContents.id))
                .leftJoin(qAdditionalContents)
                .on(mission.additionalContentsId.eq(qAdditionalContents.id))
                .leftJoin(qLimitedContents)
                .on(mission.limitedContentsId.eq(qLimitedContents.id))
                .where(mission.id.eq(missionId))
                .fetchFirst());
    }

    @Override
    public Optional<MissionMyDashboardYetVo> getMissionMyDashboardYetVo(Long missionId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(MissionMyDashboardYetVo.class,
                        mission.id,
                        mission.th,
                        mission.title,
                        mission.contents,
                        mission.guide))
                .from(mission)
                .where(mission.id.eq(missionId))
                .fetchFirst());
    }

    @Override
    public Optional<MissionMyDashboardAbsentVo> getMissionMyDashboardAbsentVo(Long missionId, Long userId) {
        QContents qEssentialContents = new QContents("essential");
        QContents qAdditionalContents = new QContents("additional");

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(MissionMyDashboardAbsentVo.class,
                        mission.id,
                        mission.th,
                        mission.title,
                        mission.contents,
                        mission.guide,
                        mission.template,
                        qEssentialContents.link,
                        qAdditionalContents.link,
                        attendance))
                .from(mission)
                .leftJoin(attendance)
                .on(
                        attendance.mission.eq(mission),
                        attendance.user.id.eq(userId)
                )
                .leftJoin(qEssentialContents)
                .on(mission.essentialContentsId.eq(qEssentialContents.id))
                .leftJoin(qAdditionalContents)
                .on(mission.additionalContentsId.eq(qAdditionalContents.id))
                .where(mission.id.eq(missionId).and(attendance.isNull()))
                .fetchFirst());
    }

    @Override
    public List<MissionAdminApplicationVo> getMissionAdminApplicationVos(Long programId, Long userId) {
        return jpaQueryFactory
                .select(Projections.constructor(MissionAdminApplicationVo.class,
                        mission.id,
                        mission.th,
                        attendance))
                .from(mission)
                .where(mission.program.id.eq(programId))
                .leftJoin(attendance)
                .on(
                        attendance.mission.eq(mission),
                        attendance.user.id.eq(userId))
                .fetch();
    }

    @Override
    public Optional<MissionDashboardVo> getDailyMission(Long programId) {
        LocalDateTime now = LocalDateTime.now();
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(MissionDashboardVo.class,
                        mission.id,
                        mission.th,
                        mission.title,
                        mission.contents,
                        mission.endDate))
                .from(mission)
                .where(
                        eqProgramId(programId),
                        inProgress(now)
                )
                .orderBy(mission.id.asc())
                .fetchFirst());
    }

    @Override
    public Optional<MissionMyDashboardVo> getDailyMissionDetail(Long programId, Long userId) {
        QContents qEssentialContents = new QContents("essential");
        QContents qAdditionalContents = new QContents("additional");
        LocalDateTime now = LocalDateTime.now();

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(MissionMyDashboardVo.class,
                        mission.id,
                        mission.th,
                        mission.title,
                        mission.contents,
                        mission.guide,
                        mission.template,
                        mission.endDate,
                        qEssentialContents.link,
                        qAdditionalContents.link,
                        attendance))
                .from(mission)
                .leftJoin(attendance)
                .on(
                        attendance.mission.eq(mission),
                        attendance.user.id.eq(userId)
                )
                .leftJoin(qEssentialContents)
                .on(qEssentialContents.id.eq(mission.essentialContentsId))
                .leftJoin(qAdditionalContents)
                .on(qAdditionalContents.id.eq(mission.additionalContentsId))
                .where(
                        eqProgramId(programId),
                        inProgress(now)
                )
                .fetchFirst());
    }

    private BooleanExpression eqProgramId(Long programId) {
        return programId != null ? mission.program.id.eq(programId) : null;
    }

    private BooleanExpression inProgress(LocalDateTime now) {
        return mission.startDate.before(now).and(mission.endDate.after(now.minusHours(6)));
    }
}
