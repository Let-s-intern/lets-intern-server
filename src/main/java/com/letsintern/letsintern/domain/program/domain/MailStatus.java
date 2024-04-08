package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.global.utils.EnumField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum MailStatus implements EnumField {
    YET(1, "YET"),
    REMIND(2, "REMIND"),
    REVIEW(3, "REVIEW");

    private final Integer code;
    private final String desc;
}
