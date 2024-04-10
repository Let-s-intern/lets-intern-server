package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.vo.program.ProgramDetailVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.letsintern.letsintern.domain.payment.domain.QPayment.payment;
import static com.letsintern.letsintern.domain.program.domain.QLetsChat.letsChat;

@Repository
@RequiredArgsConstructor
public class LetsChatQueryRepositoryImpl implements LetsChatQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<ProgramDetailVo> findLetsChatDetailVo(Long letsChatId) {
        return Optional.empty(jpaQueryFactory
                .select(Projections.constructor(ProgramDetailVo.class,
                        letsChat.id,
                        letsChat.status,
                        letsChat.title,
                        letsChat.contents,
                        letsChat.notice,
                        letsChat.way,
                        letsChat.location,
                        letsChat.dueDate,
                        letsChat.announcementDate,
                        letsChat.startDate,
                        letsChat.endDate,
                        payment.feeDueDate,
                        payment.feeType,
                        payment.feeRefund,
                        payment.feeCharge,
                        payment.discountValue,
                        payment.accountType,
                        payment.accountNumber,
                        letsChat.faqListStr,
                        letsChat.programType))
                .from(letsChat)
                .leftJoin(letsChat._super, payment.program)
                .where(letsChat.id.equals(letsChatId))
                .fetchOne());
    }
}
