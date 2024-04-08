package com.letsintern.letsintern.domain.onlineprogram.repository;

import com.letsintern.letsintern.domain.onlineprogram.vo.OnlineProgramAdminVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.letsintern.letsintern.domain.onlineprogram.domain.QOnlineProgram.onlineProgram;

@Repository
@RequiredArgsConstructor
public class OnlineProgramRepositoryImpl implements OnlineProgramRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<OnlineProgramAdminVo> findAllOnlineProgramAdminVos(Pageable pageable) {
        List<OnlineProgramAdminVo> onlineProgramAdminVoList = jpaQueryFactory
                .select(Projections.constructor(OnlineProgramAdminVo.class,
                        onlineProgram.id,
                        onlineProgram.title,
                        onlineProgram.description,
                        onlineProgram.link,
                        onlineProgram.thumbnailUrl))
                .from(onlineProgram)
                .orderBy(onlineProgram.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(onlineProgram.count())
                .from(onlineProgram);

        return PageableExecutionUtils.getPage(onlineProgramAdminVoList, pageable, count::fetchOne);
    }
}
