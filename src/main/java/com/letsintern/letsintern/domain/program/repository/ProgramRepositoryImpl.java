package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.QProgram;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProgramRepositoryImpl implements ProgramRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

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
}
