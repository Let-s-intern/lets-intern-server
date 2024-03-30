package com.letsintern.letsintern.domain.coupon.repository;

import com.letsintern.letsintern.domain.coupon.vo.CouponUserHistoryVo;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.letsintern.letsintern.domain.coupon.domain.QCouponUser.couponUser;

@RequiredArgsConstructor
public class CouponUserRepositoryCustomImpl implements CouponUserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<CouponUserHistoryVo> findCouponUserHistoryByCodeAndUserId(String code, Long userId) {
        return Optional.ofNullable(queryFactory
                .select(Projections.constructor(CouponUserHistoryVo.class,
                        couponUser.coupon,
                        couponUser.user
                ))
                .from(couponUser)
                .where(
                        eqUserId(userId),
                        eqCouponCode(code)
                )
                .fetchOne());
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ? couponUser.user.id.eq(userId) : null;
    }

    private BooleanExpression eqCouponCode(String code) {
        return code != null ? couponUser.coupon.code.eq(code) : null;
    }
}
