package com.letsintern.letsintern.domain.mission.repository;

import com.letsintern.letsintern.domain.mission.domain.QMission;
import com.letsintern.letsintern.domain.mission.vo.MissionAdminVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                        qMission.title,
                        qMission.th,
                        qMission.startDate,
                        qMission.endDate,
                        qMission.isRefunded,
                        qMission.attendanceCount,
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
}
