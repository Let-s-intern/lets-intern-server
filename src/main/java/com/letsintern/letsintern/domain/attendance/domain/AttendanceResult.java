package com.letsintern.letsintern.domain.attendance.domain;

import lombok.Getter;

public enum AttendanceResult {

    WAITING("확인중"),
    PASS("확인 완료"),
    WRONG("반려");

    @Getter
    private final String value;

    AttendanceResult(String value) {
        this.value = value;
    }
}
