package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.global.utils.EnumField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ProgramType implements EnumField {
    CHALLENGE_HALF(1, "하프 코스 챌린지"),
    CHALLENGE_FULL(2, "풀 코스 챌린지"),
    BOOTCAMP(3, "부트캠프"),
    LETS_CHAT(4, "렛츠챗"),
    ETC(5, "기타");

    private final Integer code;
    private final String desc;
}
