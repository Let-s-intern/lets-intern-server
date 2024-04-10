package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.bootcamp.BootcampDetailVo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.letsintern.letsintern.domain.payment.domain.QPayment.payment;
import static com.letsintern.letsintern.domain.program.domain.QBootcamp.bootcamp;
import static com.letsintern.letsintern.domain.program.domain.QChallenge.challenge;

@RequiredArgsConstructor
@Repository
public class BootcampQueryRepositoryImpl implements BootcampQueryRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<BootcampDetailVo> findBootcampDetailVo(Long bootcampId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(BootcampDetailVo.class,
                        bootcamp.id,
                        bootcamp.status,
                        bootcamp.title,
                        bootcamp.contents,
                        bootcamp.notice,
                        bootcamp.way,
                        bootcamp.location,
                        bootcamp.dueDate,
                        bootcamp.announcementDate,
                        bootcamp.startDate,
                        bootcamp.endDate,
                        payment.feeDueDate,
                        payment.feeRefund,
                        payment.feeCharge,
                        payment.discountValue,
                        payment.accountType,
                        payment.accountNumber,
                        bootcamp.faqListStr,
                        bootcamp.programType
                ))
                .from(bootcamp)
                .leftJoin(bootcamp._super, payment.program)
                .where(
                        eqBootcampId(bootcampId)
                ).fetchOne());
    }

    private BooleanExpression eqBootcampId(Long bootcampId) {
        return bootcampId != null ? bootcamp.id.eq(bootcampId) : null;
    }
}
