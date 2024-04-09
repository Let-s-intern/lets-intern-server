package com.letsintern.letsintern.domain.payment.domain;

import com.letsintern.letsintern.global.utils.EnumField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum FeeType implements EnumField {
    FREE(1, "무료"),
    CHARGE(2, "이용료"),
    REFUND(3, "보증금");

    private final Integer code;
    private final String desc;
}
