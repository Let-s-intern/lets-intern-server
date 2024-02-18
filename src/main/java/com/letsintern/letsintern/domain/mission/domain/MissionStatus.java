package com.letsintern.letsintern.domain.mission.domain;

import lombok.Getter;

public enum MissionStatus {

    WAITING("대기"),
    CHECK_DONE("확인 완료"),
    REFUND_DONE("환급 완료");

    @Getter
    private final String value;

    MissionStatus(String value) {
        this.value = value;
    }
}
