package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.vo.program.ProgramDetailVo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.letsintern.letsintern.domain.payment.domain.QPayment.payment;
import static com.letsintern.letsintern.domain.program.domain.QProgram.program;

@RequiredArgsConstructor
@Repository
public class ProgramRepositoryImpl implements ProgramQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

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

    private BooleanExpression eqProgramId(Long programId) {
        return programId != null ? program.id.eq(programId) : null;
    }
}
