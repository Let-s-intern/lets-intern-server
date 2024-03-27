package com.letsintern.letsintern.domain.coupon.domain;

import com.letsintern.letsintern.global.utils.EnumField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum CouponProgramType implements EnumField {
    ALL(1, "전체"),
    CHALLENGE(2, " 챌린지"),
    BOOTCAMP(3, "부트캠프"),
    SESSION(3, "렛츠챗세션");

    private final Integer code;
    private final String desc;
}
