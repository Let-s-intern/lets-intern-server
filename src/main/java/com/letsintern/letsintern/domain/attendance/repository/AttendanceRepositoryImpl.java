package com.letsintern.letsintern.domain.attendance.repository;

import com.letsintern.letsintern.domain.attendance.domain.AttendanceStatus;
import com.letsintern.letsintern.domain.attendance.domain.QAttendance;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceAdminVo;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceDashboardVo;
import com.letsintern.letsintern.domain.user.domain.QUser;
import com.letsintern.letsintern.domain.user.vo.AccountVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttendanceRepositoryImpl implements AttendanceRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<AttendanceAdminVo> getAttendanceAdminVos(Long missionId, Pageable pageable) {
        QAttendance qAttendance = QAttendance.attendance;
        List<AttendanceAdminVo> attendanceAdminVos;
        JPAQuery<Long> count;

        if(missionId != null) {
            attendanceAdminVos = jpaQueryFactory
                    .select(Projections.constructor(AttendanceAdminVo.class,
                            qAttendance.id,
                            qAttendance.user,
                            qAttendance.status,
                            qAttendance.link,
                            qAttendance.isRefunded))
                    .from(qAttendance)
                    .where(qAttendance.mission.id.eq(missionId))
                    .orderBy(qAttendance.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = jpaQueryFactory.select(qAttendance.count())
                    .from(qAttendance)
                    .where(qAttendance.mission.id.eq(missionId));
        }

        else {
            attendanceAdminVos = jpaQueryFactory
                    .select(Projections.constructor(AttendanceAdminVo.class,
                            qAttendance.id,
                            qAttendance.user,
                            qAttendance.status,
                            qAttendance.link,
                            qAttendance.isRefunded))
                    .from(qAttendance)
                    .orderBy(qAttendance.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = jpaQueryFactory.select(qAttendance.count())
                    .from(qAttendance);
        }

        return PageableExecutionUtils.getPage(attendanceAdminVos, pageable, count::fetchOne);
    }

    @Override
    public List<AttendanceDashboardVo> getAttendanceDashboardVos(Long programId, Long userId) {
        QAttendance qAttendance = QAttendance.attendance;
        return jpaQueryFactory
                .select(Projections.constructor(AttendanceDashboardVo.class,
                        qAttendance.id,
                        qAttendance.link,
                        qAttendance.mission.th,
                        qAttendance.mission.title))
                .from(qAttendance)
                .where(qAttendance.user.id.eq(userId).and(qAttendance.mission.program.id.eq(programId)))
                .orderBy(qAttendance.mission.th.asc())
                .fetch();
    }

    @Override
    public List<AccountVo> getAccountVoList(Long missionId) {
        QAttendance qAttendance = QAttendance.attendance;
        QUser qUser = QUser.user;

        return jpaQueryFactory
                .select(Projections.constructor(AccountVo.class,
                        qUser.accountType,
                        qUser.accountNumber))
                .from(qUser)
                .innerJoin(qAttendance)
                .on(qAttendance.mission.id.eq(missionId)
                        .and(qAttendance.status.eq(AttendanceStatus.PASSED))
                        .and(qAttendance.user.id.eq(qUser.id)))
                .orderBy(qAttendance.id.asc())
                .fetch();
    }
}
