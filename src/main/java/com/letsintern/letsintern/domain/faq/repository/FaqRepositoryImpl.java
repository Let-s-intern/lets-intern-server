package com.letsintern.letsintern.domain.faq.repository;

import com.letsintern.letsintern.domain.faq.domain.QFaq;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FaqRepositoryImpl implements FaqRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public FaqVo findVoById(Long id) {
        QFaq qFaq = QFaq.faq;

        return jpaQueryFactory
                .select(Projections.constructor(FaqVo.class,
                        qFaq.id,
                        qFaq.question,
                        qFaq.answer
                ))
                .from(qFaq)
                .where(qFaq.id.eq(id))
                .fetchFirst();
    }

    @Override
    public List<FaqVo> findVoListByProgramType(ProgramType programType) {
        QFaq qFaq = QFaq.faq;

        return jpaQueryFactory
                .select(Projections.constructor(FaqVo.class,
                        qFaq.id,
                        qFaq.question,
                        qFaq.answer
                ))
                .from(qFaq)
                .where(qFaq.programType.eq(programType))
                .fetch();
    }
}
