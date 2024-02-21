package com.letsintern.letsintern.domain.program.domain;

import lombok.Getter;

public enum ProgramTopic {

    ALL("전체"),
    MARKETING("마케팅"),
    DEVELOPMENT("개발");

    @Getter
    private final String value;

    ProgramTopic(String value) {
        this.value = value;
    }
}
