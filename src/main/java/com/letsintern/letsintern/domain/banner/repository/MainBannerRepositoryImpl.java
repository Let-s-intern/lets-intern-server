package com.letsintern.letsintern.domain.banner.repository;

import com.letsintern.letsintern.domain.banner.vo.MainBannerAdminVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.letsintern.letsintern.domain.banner.domain.mainbanner.domain.QMainBanner.mainBanner;

@Repository
@RequiredArgsConstructor
public class MainBannerRepositoryImpl implements MainBannerRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<MainBannerAdminVo> findAllMainBannerAdminVos(Pageable pageable) {
        List<MainBannerAdminVo> mainBannerAdminVoList = jpaQueryFactory
                .select(Projections.constructor(MainBannerAdminVo.class,
                        mainBanner.id,
                        mainBanner.title,
                        mainBanner.link,
                        mainBanner.startDate,
                        mainBanner.endDate,
                        mainBanner.isVisible,
                        mainBanner.imgUrl))
                .from(mainBanner)
                .orderBy(mainBanner.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(mainBanner.count())
                .from(mainBanner);

        return PageableExecutionUtils.getPage(mainBannerAdminVoList, pageable, count::fetchOne);
    }
}
