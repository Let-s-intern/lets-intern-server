package com.letsintern.letsintern.domain.mission.repository;

import com.letsintern.letsintern.domain.attendance.domain.QAttendance;
import com.letsintern.letsintern.domain.contents.domain.QContents;
import com.letsintern.letsintern.domain.mission.domain.QMission;
import com.letsintern.letsintern.domain.mission.vo.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<MissionAdminVo> getMissionAdminList(Long programId, Pageable pageable) {
        QMission qMission = QMission.mission;
        QContents qContents = QContents.contents;
        List<MissionAdminVo> missionAdminVos;
        JPAQuery<Long> count;

        missionAdminVos = jpaQueryFactory
                .select(Projections.constructor(MissionAdminVo.class,
                        qMission.id,
                        qMission.th,
                        qMission.title,
                        qMission.startDate,
                        qMission.endDate,
                        qMission.isRefunded,
                        qContents.topic,
                        qMission.attendanceCount,
                        qMission.status))
                .from(qMission)
                .leftJoin(qContents)
                .on(
                        qMission.essentialContentsId.eq(qContents.id)
                )
                .where(qMission.program.id.eq(programId))
                .orderBy(qMission.th.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qMission.count())
                .from(qMission)
                .where(qMission.program.id.eq(programId));

        return PageableExecutionUtils.getPage(missionAdminVos, pageable, count::fetchOne);
    }

    @Override
    public Optional<MissionAdminDetailVo> getMissionAdminDetailVo(Long missionId) {
        QMission qMission = QMission.mission;
        QContents qEssentialContents = new QContents("essential");
        QContents qAdditionalContents = new QContents("additional");
        QContents qLimitedContents = new QContents("limited");

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(MissionAdminDetailVo.class,
                        qMission.id,
                        qMission.th,
                        qMission.type,
                        qMission.topic,
                        qMission.title,
                        qMission.contents,
                        qMission.guide,
                        qMission.template,
                        qMission.startDate,
                        qMission.endDate,
                        qEssentialContents.topic,
                        qAdditionalContents.topic,
                        qLimitedContents.topic,
                        qMission.refund,
                        qMission.isRefunded))
                .from(qMission)
                        .leftJoin(qEssentialContents)
                        .on(qMission.essentialContentsId.eq(qEssentialContents.id))
                        .leftJoin(qAdditionalContents)
                        .on(qMission.additionalContentsId.eq(qAdditionalContents.id))
                        .leftJoin(qLimitedContents)
                        .on(qMission.limitedContentsId.eq(qLimitedContents.id))
                .where(qMission.id.eq(missionId))
                .fetchFirst());
    }

    @Override
    public List<MissionAdminSimpleVo> getMissionAdminSimpleList(Long programId) {
        QMission qMission = QMission.mission;
        return jpaQueryFactory
                .select(Projections.constructor(MissionAdminSimpleVo.class,
                        qMission.id,
                        qMission.th,
                        qMission.startDate,
                        qMission.attendanceCount,
                        qMission.lateAttendanceCount,
                        qMission.program.finalHeadCount,
                        qMission.status))
                .from(qMission)
                .where(qMission.program.id.eq(programId))
                .orderBy(qMission.th.asc())
                .fetch();
    }

    @Override
    public Optional<MissionDashboardVo> getMissionDashboardVo(Long programId, Integer th) {
        QMission qMission = QMission.mission;
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(MissionDashboardVo.class,
                        qMission.id,
                        qMission.th,
                        qMission.title,
                        qMission.contents,
                        qMission.guide,
                        qMission.endDate))
                .from(qMission)
                .where(qMission.program.id.eq(programId).and(qMission.th.eq(th)))
                .fetchFirst());
    }

    @Override
    public Optional<MissionMyDashboardVo> getMissionMyDashboardVo(Long programId, Integer th, Long userId) {
        QMission qMission = QMission.mission;
        QContents qEssentialContents = new QContents("essential");
        QContents qAdditionalContents = new QContents("additional");
        QAttendance qAttendance = QAttendance.attendance;
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(MissionMyDashboardVo.class,
                        qMission.id,
                        qMission.th,
                        qMission.title,
                        qMission.contents,
                        qMission.guide,
                        qMission.template,
                        qMission.endDate,
                        qEssentialContents.link,
                        qAdditionalContents.link,
                        qAttendance))
                .from(qMission)
                .leftJoin(qAttendance)
                .on(
                        qAttendance.mission.eq(qMission),
                        qAttendance.user.id.eq(userId)
                )
                .leftJoin(qEssentialContents)
                .on(qEssentialContents.id.eq(qMission.essentialContentsId))
                .leftJoin(qAdditionalContents)
                .on(qAdditionalContents.id.eq(qMission.additionalContentsId))
                .where(qMission.program.id.eq(programId).and(qMission.th.eq(th)))
                .fetchFirst());
    }

    @Override
    public List<MissionDashboardListVo> getMissionDashboardList(Long programId, Long userId) {
        QMission qMission = QMission.mission;
        QAttendance qAttendance = QAttendance.attendance;

        return jpaQueryFactory
                .select(Projections.constructor(MissionDashboardListVo.class,
                        qMission.th,
                        qMission.topic,
                        qMission.type,
                        qMission.startDate,
                        qAttendance))
                .from(qMission)
                .leftJoin(qAttendance)
                .on(
                        qAttendance.mission.eq(qMission),
                        qAttendance.user.id.eq(userId)
                )
                .where(qMission.program.id.eq(programId))
                .orderBy(qMission.th.asc())
                .fetch();
    }

    @Override
    public List<MissionMyDashboardListVo> getMissionMyDashboardList(Long programId, Long userId) {
        QMission qMission = QMission.mission;
        QAttendance qAttendance = QAttendance.attendance;
        return jpaQueryFactory
                .select(Projections.constructor(MissionMyDashboardListVo.class,
                        qMission.id,
                        qMission.th,
                        qMission.title,
                        qMission.status,
                        qMission.type,
                        qAttendance))
                .from(qMission)
                .leftJoin(qAttendance)
                .on(
                        qAttendance.mission.eq(qMission),
                        qAttendance.user.id.eq(userId)
                )
                .where(qMission.program.id.eq(programId))
                .orderBy(qMission.th.desc())
                .fetch();
    }

    @Override
    public Optional<MissionMyDashboardCompletedVo> getMissionMyDashboardCompleted(Long missionId, Long userId) {
        QMission qMission = QMission.mission;
        QAttendance qAttendance = QAttendance.attendance;
        QContents qEssentialContents = new QContents("essential");
        QContents qAdditionalContents = new QContents("additional");
        QContents qLimitedContents = new QContents("limited");

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(MissionMyDashboardCompletedVo.class,
                        qMission.id,
                        qMission.th,
                        qMission.status,
                        qMission.title,
                        qMission.contents,
                        qEssentialContents.link,
                        qAdditionalContents.link,
                        qLimitedContents.link,
                        qAttendance))
                .from(qMission)
                .leftJoin(qAttendance)
                .on(
                        qAttendance.mission.eq(qMission),
                        qAttendance.user.id.eq(userId)
                )
                .leftJoin(qEssentialContents)
                .on(qMission.essentialContentsId.eq(qEssentialContents.id))
                .leftJoin(qAdditionalContents)
                .on(qMission.additionalContentsId.eq(qAdditionalContents.id))
                .leftJoin(qLimitedContents)
                .on(qMission.limitedContentsId.eq(qLimitedContents.id))
                .where(qMission.id.eq(missionId))
                .fetchFirst());
    }

    @Override
    public Optional<MissionMyDashboardUncompletedVo> getMissionMyDashboardUncompleted(Long missionId) {
        QMission qMission = QMission.mission;

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(MissionMyDashboardUncompletedVo.class,
                        qMission.id,
                        qMission.title,
                        qMission.contents,
                        qMission.guide))
                .from(qMission)
                .where(qMission.id.eq(missionId))
                .fetchFirst());
    }
}
