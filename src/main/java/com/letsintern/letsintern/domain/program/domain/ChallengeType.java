package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.global.utils.EnumField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ChallengeType implements EnumField {
    HALF(1, "하프 코스 챌린지"),
    FULL(2, "풀 코스 챌린지");

    private final Integer code;
    private final String desc;
}
