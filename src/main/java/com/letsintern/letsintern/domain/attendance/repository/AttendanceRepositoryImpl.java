package com.letsintern.letsintern.domain.attendance.repository;

import com.letsintern.letsintern.domain.attendance.domain.QAttendance;
import com.letsintern.letsintern.domain.attendance.vo.AttendanceAdminVo;
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
                .where(qAttendance.mission.id.eq(missionId))
                .from(qAttendance);

        return PageableExecutionUtils.getPage(attendanceAdminVos, pageable, count::fetchOne);
    }
}
