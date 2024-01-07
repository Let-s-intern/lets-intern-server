package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.*;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
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
public class ProgramRepositoryImpl implements ProgramRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    @Override
    public Page<ProgramThumbnailVo> findProgramThumbnails(Pageable pageable) {
        QProgram qProgram = QProgram.program;
        List<ProgramThumbnailVo> programThumbnailVos;
        JPAQuery<Long> count;

        programThumbnailVos = jpaQueryFactory
                .select(Projections.constructor(ProgramThumbnailVo.class,
                        qProgram.id,
                        qProgram.status,
                        qProgram.type,
                        qProgram.th,
                        qProgram.title,
                        qProgram.dueDate,
                        qProgram.startDate
                ))
                .from(qProgram)
                .where(qProgram.isVisible.eq(true))
                .orderBy(qProgram.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qProgram.count())
                .from(qProgram)
                .where(qProgram.isVisible.eq(true));

        return PageableExecutionUtils.getPage(programThumbnailVos, pageable, count::fetchOne);
    }

    @Override
    public Page<ProgramThumbnailVo> findProgramThumbnailsByType(String type, Pageable pageable) {
        QProgram qProgram = QProgram.program;
        List<ProgramThumbnailVo> programThumbnailVos;
        JPAQuery<Long> count;

        if(type.equals("CHALLENGE")) {
            programThumbnailVos = jpaQueryFactory
                    .select(Projections.constructor(ProgramThumbnailVo.class,
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
                    .where(qProgram.isVisible.eq(true))
                    .orderBy(qProgram.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = jpaQueryFactory.select(qProgram.count())
                    .from(qProgram)
                    .where(qProgram.type.eq(ProgramType.CHALLENGE_HALF).or(qProgram.type.eq(ProgramType.CHALLENGE_FULL)))
                    .where(qProgram.isVisible.eq(true));

            return PageableExecutionUtils.getPage(programThumbnailVos, pageable, count::fetchOne);
        }

        programThumbnailVos = jpaQueryFactory
                .select(Projections.constructor(ProgramThumbnailVo.class,
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
                .where(qProgram.isVisible.eq(true))
                .orderBy(qProgram.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qProgram.count())
                .from(qProgram)
                .where(qProgram.type.eq(ProgramType.valueOf(type)))
                .where(qProgram.isVisible.eq(true));

        return PageableExecutionUtils.getPage(programThumbnailVos, pageable, count::fetchOne);
    }

    @Override
    public Optional<ProgramDetailVo> findProgramDetailVo(Long programId) {
        QProgram qProgram = QProgram.program;
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(ProgramDetailVo.class,
                        qProgram.status,
                        qProgram.title,
                        qProgram.contents,
                        qProgram.notice,
                        qProgram.type,
                        qProgram.way,
                        qProgram.location,
                        qProgram.faqListStr,
                        qProgram.dueDate,
                        qProgram.announcementDate,
                        qProgram.startDate,
                        qProgram.endDate
                ))
                .from(qProgram)
                .where(qProgram.id.eq(programId))
                .fetchOne());
    }

    @Override
    public Page<Program> findAllAdmin(Pageable pageable) {
        QProgram qProgram = QProgram.program;
        List<Program> programList;
        JPAQuery<Long> count;

        programList = jpaQueryFactory
                .selectFrom(qProgram)
                .orderBy(qProgram.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qProgram.count())
                .from(qProgram);

        return PageableExecutionUtils.getPage(programList, pageable, count::fetchOne);
    }

    @Override
    public Page<Program> findAllAdminByType(String type, Pageable pageable) {
        QProgram qProgram = QProgram.program;
        List<Program> programList;
        JPAQuery<Long> count;

        if(type.equals("CHALLENGE")) {
            programList = jpaQueryFactory
                    .selectFrom(qProgram)
                    .where(qProgram.type.eq(ProgramType.CHALLENGE_HALF).or(qProgram.type.eq(ProgramType.CHALLENGE_FULL)))
                    .orderBy(qProgram.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = jpaQueryFactory.select(qProgram.count())
                    .from(qProgram)
                    .where(qProgram.type.eq(ProgramType.CHALLENGE_HALF).or(qProgram.type.eq(ProgramType.CHALLENGE_FULL)));

            return PageableExecutionUtils.getPage(programList, pageable, count::fetchOne);
        }

        programList = jpaQueryFactory
                .selectFrom(qProgram)
                .where(qProgram.type.eq(ProgramType.valueOf(type)))
                .orderBy(qProgram.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qProgram.count())
                .from(qProgram)
                .where(qProgram.type.eq(ProgramType.valueOf(type)));

        return PageableExecutionUtils.getPage(programList, pageable, count::fetchOne);
    }



    @Override
    public Page<Program> findAllAdminByTypeAndTh(String type, Integer th, Pageable pageable) {
        QProgram qProgram = QProgram.program;
        List<Program> programList;
        JPAQuery<Long> count;

        if(type.equals("CHALLENGE")) {
            programList = jpaQueryFactory
                    .selectFrom(qProgram)
                    .where(qProgram.type.eq(ProgramType.CHALLENGE_HALF).or(qProgram.type.eq(ProgramType.CHALLENGE_FULL)))
                    .where(qProgram.th.eq(th))
                    .orderBy(qProgram.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = jpaQueryFactory.select(qProgram.count())
                    .from(qProgram)
                    .where(qProgram.type.eq(ProgramType.CHALLENGE_HALF).or(qProgram.type.eq(ProgramType.CHALLENGE_FULL)))
                    .where(qProgram.th.eq(th));

            return PageableExecutionUtils.getPage(programList, pageable, count::fetchOne);
        }

        programList = jpaQueryFactory
                .selectFrom(qProgram)
                .where(qProgram.type.eq(ProgramType.valueOf(type)), qProgram.th.eq(th))
                .where(qProgram.th.eq(th))
                .orderBy(qProgram.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qProgram.count())
                .from(qProgram)
                .where(qProgram.type.eq(ProgramType.valueOf(type)), qProgram.th.eq(th))
                .where(qProgram.th.eq(th));

        return PageableExecutionUtils.getPage(programList, pageable, count::fetchOne);
    }

    @Override
    public List<Long> findProgramIdAndUpdateStatusToDone(LocalDateTime now) {
        QProgram qProgram = QProgram.program;

        List<Long> programIdList = jpaQueryFactory
                .select(qProgram.id).from(qProgram)
                .where(qProgram.status.eq(ProgramStatus.CLOSED), qProgram.endDate.before(now))
                .fetch();

        jpaQueryFactory
                .update(qProgram)
                .set(qProgram.status, ProgramStatus.DONE)
                .where(qProgram.status.eq(ProgramStatus.CLOSED), qProgram.endDate.before(now))
                .execute();

        em.flush();
        em.clear();

        return programIdList;
    }

    @Override
    public void updateAllByDueDate(LocalDateTime now) {
        QProgram qProgram = QProgram.program;

        jpaQueryFactory
            .update(qProgram)
            .set(qProgram.status, ProgramStatus.CLOSED)
            .where(qProgram.status.eq(ProgramStatus.OPEN), qProgram.dueDate.before(now))
            .execute();

        em.flush();
        em.clear();
    }

}
