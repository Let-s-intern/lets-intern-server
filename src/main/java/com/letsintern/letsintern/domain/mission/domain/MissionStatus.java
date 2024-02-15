package com.letsintern.letsintern.domain.mission.domain;

import lombok.Getter;

public enum MissionStatus {

    CREATED("대기"),
    IN_PROGRESS("진행중"),
    DONE("종료");

    @Getter
    private final String value;

    MissionStatus(String value) {
        this.value = value;
    }
}
