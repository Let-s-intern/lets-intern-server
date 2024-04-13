package com.letsintern.letsintern.domain.program.repository;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.vo.bootcamp.BootcampDetailVo;
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

import static com.letsintern.letsintern.domain.program.domain.QBootcamp.bootcamp;

@RequiredArgsConstructor
@Repository
public class BootcampQueryRepositoryImpl implements BootcampQueryRepository {
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
                        bootcamp.zoomLink,
                        bootcamp.zoomLinkPassword,
                        bootcamp.dueDate,
                        bootcamp.announcementDate,
                        bootcamp.startDate,
                        bootcamp.endDate,
                        bootcamp.payment.feeDueDate,
                        bootcamp.payment.feeRefund,
                        bootcamp.payment.feeCharge,
                        bootcamp.payment.discountValue,
                        bootcamp.payment.accountType,
                        bootcamp.payment.accountNumber,
                        bootcamp.faqListStr,
                        bootcamp.programType
                ))
                .from(bootcamp)
                .where(
                        eqBootcampId(bootcampId)
                ).fetchOne());
    }

    @Override
    public Page<ProgramThumbnailVo> findProgramThumbnailsByType(Pageable pageable) {
        List<ProgramThumbnailVo> programThumbnailVos = jpaQueryFactory
                .select(Projections.constructor(ProgramThumbnailVo.class,
                        bootcamp.id,
                        bootcamp.status,
                        bootcamp.programType,
                        bootcamp.th,
                        bootcamp.title,
                        bootcamp.dueDate,
                        bootcamp.startDate
                ))
                .from(bootcamp)
                .where(
                        eqProgramType(ProgramType.BOOTCAMP),
                        eqIsVisible(Boolean.TRUE)
                )
                .orderBy(bootcamp.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory.select(bootcamp.count())
                .from(bootcamp)
                .where(
                        eqProgramType(ProgramType.BOOTCAMP),
                        eqIsVisible(Boolean.TRUE)
                );

        return PageableExecutionUtils.getPage(programThumbnailVos, pageable, count::fetchOne);
    }

    private BooleanExpression eqProgramType(ProgramType programType) {
        return programType != null ? bootcamp.programType.eq(programType) : null;
    }

    private BooleanExpression eqIsVisible(Boolean isVisible) {
        return isVisible != null ? bootcamp.isVisible.eq(isVisible) : null;
    }

    private BooleanExpression eqBootcampId(Long bootcampId) {
        return bootcampId != null ? bootcamp.id.eq(bootcampId) : null;
    }
}
