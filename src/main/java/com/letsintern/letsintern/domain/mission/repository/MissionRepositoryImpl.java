package com.letsintern.letsintern.domain.mission.repository;

import com.letsintern.letsintern.domain.attendance.domain.QAttendance;
import com.letsintern.letsintern.domain.mission.domain.MissionDashboardListStatus;
import com.letsintern.letsintern.domain.mission.domain.QMission;
import com.letsintern.letsintern.domain.mission.vo.*;
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
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public Page<MissionAdminVo> getMissionAdminList(Long programId, Pageable pageable) {
        QMission qMission = QMission.mission;
        List<MissionAdminVo> missionAdminVos;
        JPAQuery<Long> count;

        missionAdminVos = jpaQueryFactory
                .select(Projections.constructor(MissionAdminVo.class,
                        qMission.id,
                        qMission.th,
                        qMission.title,
                        qMission.contents,
                        qMission.guide,
                        qMission.template,
                        qMission.startDate,
                        qMission.endDate,
                        qMission.isRefunded,
                        qMission.contentsListStr,
                        qMission.attendanceCount,
                        qMission.isVisible,
                        qMission.status))
                .from(qMission)
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
                        qMission.contentsListStr,
                        qAttendance))
                .from(qMission)
                .leftJoin(qAttendance)
                .on(
                        qAttendance.mission.eq(qMission),
                        qAttendance.user.id.eq(userId)
                )
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
    public List<MissionMyDashboardListVo> getMissionMyDashboardList(Long programId, Long userId, MissionDashboardListStatus status) {
        QMission qMission = QMission.mission;
        QAttendance qAttendance = QAttendance.attendance;

        switch (status) {
            case DONE -> {
                return jpaQueryFactory
                        .select(Projections.constructor(MissionMyDashboardListVo.class,
                                qMission.id,
                                qMission.th,
                                qMission.title))
                        .from(qMission)
                        .leftJoin(qAttendance)
                        .on(
                                qAttendance.mission.eq(qMission),
                                qAttendance.user.id.eq(userId)
                        )
                        .where(qMission.program.id.eq(programId))
                        .where(qAttendance.isNotNull())
                        .orderBy(qMission.th.desc())
                        .fetch();
            }

            case ABSENT -> {
                return jpaQueryFactory
                        .select(Projections.constructor(MissionMyDashboardListVo.class,
                                qMission.id,
                                qMission.th,
                                qMission.title))
                        .from(qMission)
                        .leftJoin(qAttendance)
                        .on(
                                qAttendance.mission.eq(qMission),
                                qAttendance.user.id.eq(userId)
                        )
                        .where(qMission.program.id.eq(programId).and(qMission.endDate.before(LocalDateTime.now())))
                        .where(qAttendance.isNull())
                        .orderBy(qMission.th.desc())
                        .fetch();
            }

            case YET -> {
                return jpaQueryFactory
                        .select(Projections.constructor(MissionMyDashboardListVo.class,
                                qMission.id,
                                qMission.th,
                                qMission.title))
                        .from(qMission)
                        .leftJoin(qAttendance)
                        .on(
                                qAttendance.mission.eq(qMission),
                                qAttendance.user.id.eq(userId)
                        )
                        .where(qMission.program.id.eq(programId).and(qMission.endDate.after(LocalDateTime.now())))
                        .where(qAttendance.isNull())
                        .orderBy(qMission.th.desc())
                        .fetch();
            }
        }

        return null;
    }

    @Override
    public Optional<MissionMyDashboardCompletedVo> getMissionMyDashboardCompleted(Long missionId, Long userId) {
        QMission qMission = QMission.mission;
        QAttendance qAttendance = QAttendance.attendance;

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(MissionMyDashboardCompletedVo.class,
                        qMission.id,
                        qMission.th,
                        qMission.title,
                        qMission.contents,
                        qMission.contentsListStr,
                        qAttendance))
                .from(qMission)
                .leftJoin(qAttendance)
                .on(
                        qAttendance.mission.eq(qMission),
                        qAttendance.user.id.eq(userId)
                )
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
