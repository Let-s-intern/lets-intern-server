package com.letsintern.letsintern.domain.banner.repository;

import com.letsintern.letsintern.domain.banner.vo.BannerVo;
import com.letsintern.letsintern.domain.banner.vo.PopupAdminVo;
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

import static com.letsintern.letsintern.domain.banner.domain.QPopup.popup;

@Repository
@RequiredArgsConstructor
public class PopupRepositoryImpl implements PopupRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<PopupAdminVo> findAllPopupAdminVos(Pageable pageable) {
        List<PopupAdminVo> popupAdminVoList = jpaQueryFactory
                .select(Projections.constructor(PopupAdminVo.class,
                        popup.id,
                        popup.title,
                        popup.link,
                        popup.startDate,
                        popup.endDate,
                        popup.isVisible,
                        popup.imgUrl))
                .from(popup)
                .orderBy(popup.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(popup.count())
                .from(popup);

        return PageableExecutionUtils.getPage(popupAdminVoList, pageable, count::fetchOne);
    }

    @Override
    public Page<BannerVo> findValidAndVisibleBanner(Pageable pageable) {
        List<BannerVo> bannerVoList = jpaQueryFactory
                .select(Projections.constructor(BannerVo.class,
                        popup.id,
                        popup.title,
                        popup.link,
                        popup.imgUrl))
                .from(popup)
                .where(
                        eqIsValid(true),
                        eqIsVisible(true)
                )
                .orderBy(popup.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = jpaQueryFactory
                .select(popup.count())
                .where(
                        eqIsValid(true),
                        eqIsVisible(true));

        return PageableExecutionUtils.getPage(bannerVoList, pageable, count::fetchOne);
    }

    private BooleanExpression eqIsValid(Boolean isValid) {
        return isValid != null ? popup.isValid : null;
    }

    private BooleanExpression eqIsVisible(Boolean isVisible) {
        return isVisible != null ? popup.isVisible : null;
    }
}
