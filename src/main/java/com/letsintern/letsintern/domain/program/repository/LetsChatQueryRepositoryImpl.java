package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.LetsChat;
import com.letsintern.letsintern.domain.program.domain.MailStatus;
import com.letsintern.letsintern.domain.program.vo.letschat.LetsChatDetailVo;
import com.letsintern.letsintern.domain.program.vo.letschat.LetsChatMentorInfoVo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.letsintern.letsintern.domain.payment.domain.QPayment.payment;
import static com.letsintern.letsintern.domain.program.domain.QLetsChat.letsChat;

@Repository
@RequiredArgsConstructor
public class LetsChatQueryRepositoryImpl implements LetsChatQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<LetsChatDetailVo> findLetsChatDetailVo(Long letsChatId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(LetsChatDetailVo.class,
                        letsChat.id,
                        letsChat.status,
                        letsChat.title,
                        letsChat.contents,
                        letsChat.notice,
                        letsChat.way,
                        letsChat.location,
                        letsChat.zoomLink,
                        letsChat.zoomLinkPassword,
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
                .where(eqLetsChatId(letsChatId))
                .fetchOne());
    }

    @Override
    public Optional<LetsChatMentorInfoVo> findLetsChatMentorInfoVo(Long letsChatId) {
        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(LetsChatMentorInfoVo.class,
                        letsChat.title,
                        letsChat.way,
                        letsChat.location,
                        letsChat.zoomLink,
                        letsChat.zoomLinkPassword,
                        letsChat.startDate,
                        letsChat.endDate,
                        letsChat.applicationCount))
                .from(letsChat)
                .where(eqLetsChatId(letsChatId))
                .fetchOne());
    }

    @Override
    public List<LetsChat> findAllLetsChatByMailStatusAndStartDate(MailStatus mailStatus, LocalDate startDate) {
        return null;
    }

    @Override
    public List<LetsChat> findAllLetsChatByMailStatusAndEndDate(MailStatus mailStatus, LocalDateTime endDate) {
        return null;
    }

    private BooleanExpression eqLetsChatId(Long letsChatId) {
        return letsChatId != null ? letsChat.id.eq(letsChatId) : null;
    }
}
