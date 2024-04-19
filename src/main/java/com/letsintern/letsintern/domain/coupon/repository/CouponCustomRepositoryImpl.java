package com.letsintern.letsintern.domain.coupon.repository;

import com.letsintern.letsintern.domain.coupon.domain.Coupon;
import com.letsintern.letsintern.domain.coupon.domain.CouponUser;
import com.letsintern.letsintern.domain.coupon.vo.CouponAdminVo;
import com.letsintern.letsintern.domain.coupon.vo.CouponUserHistoryVo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Optional;

import static com.letsintern.letsintern.domain.coupon.domain.QCoupon.coupon;
import static com.letsintern.letsintern.domain.coupon.domain.QCouponUser.couponUser;

@RequiredArgsConstructor
public class CouponCustomRepositoryImpl implements CouponCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<CouponAdminVo> findCouponAdminInfo(Pageable pageable) {
        List<CouponAdminVo> content = queryFactory
                .select(Projections.constructor(CouponAdminVo.class,
                        coupon.id,
                        coupon.couponType,
                        coupon.name,
                        coupon.code,
                        coupon.discount,
                        coupon.time,
                        coupon.startDate,
                        coupon.endDate,
                        coupon.createDate
                ))
                .from(coupon)
                .orderBy(coupon.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Coupon> countQuery = queryFactory
                .selectFrom(coupon);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    @Override
    public Optional<Coupon> existCouponCodeExceptedCouponId(Long couponId, String code) {
        return Optional.ofNullable(queryFactory
                .selectFrom(coupon)
                .where(
                        neCouponId(couponId),
                        eqCouponCode(code)
                )
                .fetchOne());
    }

    private BooleanExpression neCouponId(Long couponId) {
        return couponId != null ? coupon.id.ne(couponId) : null;
    }

    private BooleanExpression eqCouponCode(String code) {
        return code != null ? coupon.code.eq(code) : null;
    }
}
