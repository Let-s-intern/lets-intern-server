package com.letsintern.letsintern.domain.banner.repository;

import com.letsintern.letsintern.domain.banner.vo.LineBannerAdminVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.letsintern.letsintern.domain.banner.domain.QLineBanner.lineBanner;

@Repository
@RequiredArgsConstructor
public class LineBannerRepositoryImpl implements LineBannerRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<LineBannerAdminVo> findAllLineBannerAdminVos(Pageable pageable) {
        List<LineBannerAdminVo> lineBannerAdminVoList = jpaQueryFactory
                .select(Projections.constructor(LineBannerAdminVo.class,
                        lineBanner.id,
                        lineBanner.title,
                        lineBanner.link,
                        lineBanner.startDate,
                        lineBanner.endDate,
                        lineBanner.isVisible,
                        lineBanner.contents,
                        lineBanner.colorCode,
                        lineBanner.textColorCode))
                .from(lineBanner)
                .orderBy(lineBanner.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(lineBanner.count())
                .from(lineBanner);

        return PageableExecutionUtils.getPage(lineBannerAdminVoList, pageable, count::fetchOne);
    }
}
