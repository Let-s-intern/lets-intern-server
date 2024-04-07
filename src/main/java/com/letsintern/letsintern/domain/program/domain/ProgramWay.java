package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.global.utils.EnumField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ProgramWay implements EnumField {
    ONLINE(1, "온라인"),
    OFFLINE(2, "오프라인"),
    ALL(3, "온/오프라인 병행");

    private final Integer code;
    private final String desc;
}
