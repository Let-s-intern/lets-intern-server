package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.global.utils.EnumField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ProgramStatus implements EnumField {
    OPEN(1, "모집중"),
    CLOSED(2, "모집완료"),
    DONE(3, "진행완료");

    private final Integer code;
    private final String desc;
}
