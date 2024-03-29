package com.letsintern.letsintern.domain.coupon.repository;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.coupon.vo.CouponAdminVo;
import com.letsintern.letsintern.domain.coupon.vo.CouponUserHistoryVo;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.letsintern.letsintern.domain.coupon.domain.QCoupon.coupon;
import static com.letsintern.letsintern.domain.coupon.domain.QCouponUser.couponUser;

@RequiredArgsConstructor
public class CouponCustomRepositoryImpl implements CouponCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CouponAdminVo> findCouponAdminInfo(Pageable pageable) {
        List<CouponAdminVo> content = queryFactory
                .select(Projections.constructor(CouponAdminVo.class,
                        coupon.couponType,
                        coupon.couponProgramType,
                        coupon.name,
                        coupon.code,
                        coupon.discount,
                        coupon.time,
                        coupon.startDate,
                        coupon.endDate,
                        coupon.createDate
                ))
                .from(couponUser)
                .orderBy(coupon.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Coupon> countQuery = queryFactory
                .selectFrom(coupon);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }
}
