package com.letsintern.letsintern.domain.coupon.repository;

import com.letsintern.letsintern.domain.coupon.domain.QCoupon;
import com.letsintern.letsintern.domain.coupon.domain.QCouponUser;
import com.letsintern.letsintern.domain.coupon.vo.CouponUserHistoryVo;
import com.letsintern.letsintern.domain.user.domain.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.letsintern.letsintern.domain.coupon.domain.QCoupon.coupon;
import static com.letsintern.letsintern.domain.user.domain.QUser.user;
import static com.letsintern.letsintern.domain.coupon.domain.QCouponUser.couponUser;

@RequiredArgsConstructor
public class CouponRepositoryCustomImpl implements CouponRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<CouponUserHistoryVo> findCouponUserHistoryByCodeAndUserId(String code, Long userId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(CouponUserHistoryVo.class,
                        couponUser.id,
                        user.id,
                        coupon.discount,
                        couponUser.remainTime,
                        coupon.startDate,
                        coupon.endDate
                ))
                .from(couponUser)
                .leftJoin(couponUser.coupon, coupon)
                .leftJoin(couponUser.user, user)
                .where(
                        eqUserId(userId),
                        eqCouponCode(code)
                )
                .fetchOne());
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? user.id.eq(userId) : null;
    }

    private BooleanExpression eqCouponCode(String code) {
        return code != null ? coupon.code.eq(code) : null;
    }
}
