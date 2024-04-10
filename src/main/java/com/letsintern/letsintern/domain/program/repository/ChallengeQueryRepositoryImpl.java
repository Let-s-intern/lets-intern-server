package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.vo.challenge.ChallengeDetailVo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.letsintern.letsintern.domain.payment.domain.QPayment.payment;
import static com.letsintern.letsintern.domain.program.domain.QChallenge.challenge;

@Repository
@RequiredArgsConstructor
public class ChallengeQueryRepositoryImpl implements ChallengeQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<ChallengeDetailVo> findChallengeDetailVo(Long challengeId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(ChallengeDetailVo.class,
                        challenge.id,
                        challenge.status,
                        challenge.title,
                        challenge.contents,
                        challenge.notice,
                        challenge.way,
                        challenge.location,
                        challenge.topic,
                        challenge.dueDate,
                        challenge.announcementDate,
                        challenge.startDate,
                        challenge.endDate,
                        payment.feeDueDate,
                        payment.feeType,
                        payment.feeRefund,
                        payment.feeCharge,
                        payment.discountValue,
                        payment.accountType,
                        payment.accountNumber,
                        challenge.faqListStr,
                        challenge.programType
                ))
                .from(challenge)
                .leftJoin(challenge._super, payment.program)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetchOne());
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }
}
