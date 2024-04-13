package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.vo.challenge.ChallengeDetailVo;
import com.letsintern.letsintern.domain.program.vo.program.ProgramThumbnailVo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
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
                        challenge.zoomLink,
                        challenge.zoomLinkPassword,
                        challenge.topic,
                        challenge.dueDate,
                        challenge.announcementDate,
                        challenge.startDate,
                        challenge.endDate,
                        challenge.payment.feeDueDate,
                        challenge.payment.feeType,
                        challenge.payment.feeRefund,
                        challenge.payment.feeCharge,
                        challenge.payment.discountValue,
                        challenge.payment.accountType,
                        challenge.payment.accountNumber,
                        challenge.faqListStr,
                        challenge.programType
                ))
                .from(challenge)
                .leftJoin(challenge.payment, payment)
                .where(
                        eqChallengeId(challengeId)
                )
                .fetchOne());
    }

    @Override
    public Page<ProgramThumbnailVo> findProgramThumbnailsByType(Pageable pageable) {
        List<ProgramThumbnailVo> programThumbnailVos = jpaQueryFactory
                .select(Projections.constructor(ProgramThumbnailVo.class,
                        challenge.id,
                        challenge.status,
                        challenge.programType,
                        challenge.th,
                        challenge.title,
                        challenge.dueDate,
                        challenge.startDate
                ))
                .from(challenge)
                .where(
                        eqProgramType(ProgramType.CHALLENGE_FULL).or(eqProgramType(ProgramType.CHALLENGE_HALF)),
                        eqIsVisible(Boolean.TRUE)
                )
                .orderBy(challenge.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory.select(challenge.count())
                .from(challenge)
                .where(
                        eqProgramType(ProgramType.CHALLENGE_FULL).or(eqProgramType(ProgramType.CHALLENGE_HALF)),
                        eqIsVisible(Boolean.TRUE)
                );

        return PageableExecutionUtils.getPage(programThumbnailVos, pageable, count::fetchOne);
    }

    private BooleanExpression eqProgramType(ProgramType programType) {
        return programType != null ? challenge.programType.eq(programType) : null;
    }

    private BooleanExpression eqIsVisible(Boolean isVisible) {
        return isVisible != null ? challenge.isVisible.eq(isVisible) : null;
    }

    private BooleanExpression eqChallengeId(Long challengeId) {
        return challengeId != null ? challenge.id.eq(challengeId) : null;
    }
}
