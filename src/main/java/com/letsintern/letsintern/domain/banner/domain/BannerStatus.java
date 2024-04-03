package com.letsintern.letsintern.domain.banner.domain;

import com.letsintern.letsintern.global.utils.EnumField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum BannerStatus implements EnumField {

    VALID(0, "유효"),
    INVALID(1, "무효");

    private final Integer code;
    private final String desc;
}
