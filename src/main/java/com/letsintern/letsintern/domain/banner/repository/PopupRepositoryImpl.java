package com.letsintern.letsintern.domain.banner.repository;

import com.letsintern.letsintern.domain.banner.vo.PopupAdminVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.letsintern.letsintern.domain.banner.domain.popup.domain.QPopup.popup;

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
}
