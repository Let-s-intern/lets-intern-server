package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.QProgram;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import com.letsintern.letsintern.domain.program.vo.QProgramThumbnailVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProgramRepositoryImpl implements ProgramRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    @Override
    public Integer maxTh(ProgramType type) {
        QProgram qProgram = QProgram.program;
        Integer maxTh = jpaQueryFactory
                            .select(qProgram.th.max())
                            .from(qProgram)
                            .where(qProgram.type.eq(type))
                            .fetchOne();

        return (maxTh == null) ? 0 : maxTh;
    }

    @Override
    public List<ProgramThumbnailVo> findProgramThumbnails(Pageable pageable) {
        QProgram qProgram = QProgram.program;
        return jpaQueryFactory
                .select(new QProgramThumbnailVo(
                        qProgram.id,
                        qProgram.status,
                        qProgram.type,
                        qProgram.th,
                        qProgram.title,
                        qProgram.dueDate,
                        qProgram.startDate
                ))
                .from(qProgram)
                .where(qProgram.isApproved.eq(true), qProgram.isVisible.eq(true))
                .orderBy(qProgram.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<ProgramThumbnailVo> findProgramThumbnailsByType(String type, Pageable pageable) {
        QProgram qProgram = QProgram.program;
        if(type.equals("CHALLENGE")) {
            return jpaQueryFactory
                    .select(new QProgramThumbnailVo(
                            qProgram.id,
                            qProgram.status,
                            qProgram.type,
                            qProgram.th,
                            qProgram.title,
                            qProgram.dueDate,
                            qProgram.startDate
                    ))
                    .from(qProgram)
                    .where(qProgram.type.eq(ProgramType.CHALLENGE_HALF).or(qProgram.type.eq(ProgramType.CHALLENGE_FULL)))
                    .where(qProgram.isApproved.eq(true), qProgram.isVisible.eq(true))
                    .orderBy(qProgram.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }

        return jpaQueryFactory
                .select(new QProgramThumbnailVo(
                        qProgram.id,
                        qProgram.status,
                        qProgram.type,
                        qProgram.th,
                        qProgram.title,
                        qProgram.dueDate,
                        qProgram.startDate
                ))
                .from(qProgram)
                .where(qProgram.type.eq(ProgramType.valueOf(type)))
                .where(qProgram.isApproved.eq(true), qProgram.isVisible.eq(true))
                .orderBy(qProgram.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Optional<ProgramDetailVo> findProgramDetailVo(Long programId) {
        QProgram qProgram = QProgram.program;
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(ProgramDetailVo.class,
                        qProgram.title,
                        qProgram.contents,
                        qProgram.notice
                ))
                .from(qProgram)
                .where(qProgram.id.eq(programId))
                .fetchOne());
    }

    @Override
    public List<Program> findAllAdmin(Pageable pageable) {
        QProgram qProgram = QProgram.program;
        return jpaQueryFactory
                .selectFrom(qProgram)
                .orderBy(qProgram.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<Program> findAllAdminByType(String type, Pageable pageable) {
        QProgram qProgram = QProgram.program;
        if(type.equals("CHALLENGE")) {
            return jpaQueryFactory
                    .selectFrom(qProgram)
                    .where(qProgram.type.eq(ProgramType.CHALLENGE_HALF).or(qProgram.type.eq(ProgramType.CHALLENGE_FULL)))
                    .orderBy(qProgram.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }
        return jpaQueryFactory
                .selectFrom(qProgram)
                .where(qProgram.type.eq(ProgramType.valueOf(type)))
                .orderBy(qProgram.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public void updateAllByDueDate(Date now) {
        QProgram qProgram = QProgram.program;

        jpaQueryFactory
            .update(qProgram)
            .set(qProgram.status, ProgramStatus.CLOSED)
            .where(qProgram.status.eq(ProgramStatus.OPEN), qProgram.dueDate.before(now))
            .execute();

        em.flush();
        em.clear();
    }

    @Override
    public List<Program> findAllAdminByTypeAndTh(String type, Integer th, Pageable pageable) {
        QProgram qProgram = QProgram.program;
        if(type.equals("CHALLENGE")) {
            return jpaQueryFactory
                    .selectFrom(qProgram)
                    .where(qProgram.type.eq(ProgramType.CHALLENGE_HALF).or(qProgram.type.eq(ProgramType.CHALLENGE_FULL)))
                    .where(qProgram.th.eq(th))
                    .orderBy(qProgram.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }
        return jpaQueryFactory
                .selectFrom(qProgram)
                .where(qProgram.type.eq(ProgramType.valueOf(type)), qProgram.th.eq(th))
                .orderBy(qProgram.id.desc())
                .where(qProgram.th.eq(th))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
