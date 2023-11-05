package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.QProgram;
import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import com.letsintern.letsintern.domain.program.vo.QProgramThumbnailVo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

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

    @Override
    public List<ProgramThumbnailVo> findProgramThumbnailByStatus(ProgramStatus status, Pageable pageable) {
        QProgram qProgram = QProgram.program;

        return jpaQueryFactory
                .select(new QProgramThumbnailVo(
                        qProgram.id,
                        qProgram.type,
                        qProgram.th,
                        qProgram.dueDate,
                        qProgram.startDate
                ))
                .from(qProgram)
                .where(qProgram.status.eq(status))
                .orderBy(qProgram.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<ProgramThumbnailVo> findProgramThumbnailByTypeAndStatus(String type, ProgramStatus status, Pageable pageable) {
        QProgram qProgram = QProgram.program;

        if(type.equals("CHALLENGE")) {
            return jpaQueryFactory
                    .select(new QProgramThumbnailVo(
                            qProgram.id,
                            qProgram.type,
                            qProgram.th,
                            qProgram.dueDate,
                            qProgram.startDate
                    ))
                    .from(qProgram)
                    .where(qProgram.type.eq(ProgramType.CHALLENGE_HALF).or(qProgram.type.eq(ProgramType.CHALLENGE_FULL)), qProgram.status.eq(status))
                    .orderBy(qProgram.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }

        return jpaQueryFactory
                .select(new QProgramThumbnailVo(
                        qProgram.id,
                        qProgram.type,
                        qProgram.th,
                        qProgram.dueDate,
                        qProgram.startDate
                ))
                .from(qProgram)
                .where(qProgram.type.eq(ProgramType.valueOf(type)), qProgram.status.eq(status))
                .orderBy(qProgram.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
