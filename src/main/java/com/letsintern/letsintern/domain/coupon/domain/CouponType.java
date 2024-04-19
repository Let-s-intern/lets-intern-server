package com.letsintern.letsintern.domain.coupon.domain;

import com.letsintern.letsintern.global.utils.EnumField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CouponType implements EnumField {
    PARTNERSHIP(1, "제휴"),
    EVENT(2, "이벤트"),
    GRADE(3, "등급별할인");

    private final Integer code;
    private final String desc;
}
