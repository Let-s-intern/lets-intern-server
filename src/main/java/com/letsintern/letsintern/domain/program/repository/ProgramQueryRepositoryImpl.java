package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.QProgram;
import com.letsintern.letsintern.domain.program.vo.program.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.program.UserProgramVo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
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

import static com.letsintern.letsintern.domain.payment.domain.QPayment.payment;
import static com.letsintern.letsintern.domain.program.domain.QProgram.program;

@RequiredArgsConstructor
@Repository
public class ProgramQueryRepositoryImpl implements ProgramQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    @Override
    public Optional<ProgramDetailVo> findProgramDetailVo(Long programId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(ProgramDetailVo.class,
                        program.id,
                        program.status,
                        program.title,
                        program.contents,
                        program.notice,
                        program.way,
                        program.location,
                        program.dueDate,
                        program.announcementDate,
                        program.startDate,
                        program.endDate,
                        payment.feeDueDate,
                        payment.feeRefund,
                        payment.feeCharge,
                        payment.discountValue,
                        payment.accountType,
                        payment.accountNumber,
                        program.faqListStr,
                        program.programType
                ))
                .from(program)
                .leftJoin(program.payment, payment)
                .where(
                        eqProgramId(programId)
                ).fetchOne());
    }

    @Override
    public void updateAllProgramStatusClosedByDueDate(LocalDateTime now) {
        jpaQueryFactory
                .update(program)
                .set(program.status, ProgramStatus.CLOSED)
                .where(
                        eqProgramStatus(ProgramStatus.OPEN),
                        isClosedProgram(now)
                )
                .execute();

        em.flush();
        em.clear();
    }

    @Override
    public List<Long> findAllProgramIdListAndUpdateStatusToDone(LocalDateTime now) {
        List<Long> programIdList = jpaQueryFactory
                .select(program.id).from(program)
                .where(
                        eqProgramStatus(ProgramStatus.CLOSED),
                        isDoneProgram(now)
                )
                .fetch();

        jpaQueryFactory
                .update(program)
                .set(program.status, ProgramStatus.DONE)
                .where(
                        eqProgramStatus(ProgramStatus.CLOSED),
                        isDoneProgram(now)
                )
                .execute();

        em.flush();
        em.clear();

        return programIdList;
    }

    @Override
    public Page<Program> findAllAdminByTypeAndTh(String type, Integer th, Pageable pageable) {
        List<Program> programList;
        JPAQuery<Long> count;

        if (type.equals("CHALLENGE")) {
            programList = jpaQueryFactory
                    .selectFrom(program)
                    .where(program.programType.eq(ProgramType.CHALLENGE_HALF).or(program.programType.eq(ProgramType.CHALLENGE_FULL)))
                    .where(program.th.eq(th))
                    .orderBy(program.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = jpaQueryFactory.select(program.count())
                    .from(program)
                    .where(program.programType.eq(ProgramType.CHALLENGE_HALF).or(program.programType.eq(ProgramType.CHALLENGE_FULL)))
                    .where(program.th.eq(th));

            return PageableExecutionUtils.getPage(programList, pageable, count::fetchOne);
        }

        programList = jpaQueryFactory
                .selectFrom(program)
                .where(program.programType.eq(ProgramType.valueOf(type)), program.th.eq(th))
                .where(program.th.eq(th))
                .orderBy(program.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(program.count())
                .from(program)
                .where(program.programType.eq(ProgramType.valueOf(type)), program.th.eq(th))
                .where(program.th.eq(th));

        return PageableExecutionUtils.getPage(programList, pageable, count::fetchOne);
    }

    private BooleanExpression eqProgramId(Long programId) {
        return programId != null ? program.id.eq(programId) : null;
    }

    private BooleanExpression eqProgramStatus(ProgramStatus programStatus) {
        return program.status.eq(programStatus);
    }

    private BooleanExpression isClosedProgram(LocalDateTime now) {
        return program.dueDate.before(now);
    }

    private BooleanExpression isDoneProgram(LocalDateTime now) {
        return program.endDate.before(now);
    }
}
