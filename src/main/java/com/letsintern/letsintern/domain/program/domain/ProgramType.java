package com.letsintern.letsintern.domain.program.domain;

import lombok.Getter;

@Getter
public enum ProgramType {

    CHALLENGE_HALF("하프 코스 챌린지"),
    CHALLENGE_FULL("풀 코스 챌린지"),
    BOOTCAMP("부트캠프"),
    LETS_CHAT("렛츠챗"),
    ETC("기타");

    private String value;

    ProgramType(String value) {
        this.value = value;
    }
}
