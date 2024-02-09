package com.letsintern.letsintern.domain.mission.domain;

import lombok.Getter;

public enum MissionStatus {

    CREATED("대기"),
    IN_PROGRESS("진행중"),
    REFUND_WAITING("환급대기"),
    REFUND_DONE("환급완료");

    @Getter
    private final String value;

    MissionStatus(String value) {
        this.value = value;
    }
}
