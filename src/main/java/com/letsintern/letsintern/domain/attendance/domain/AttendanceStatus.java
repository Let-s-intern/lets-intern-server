package com.letsintern.letsintern.domain.attendance.domain;

import lombok.Getter;

public enum AttendanceStatus {

    PRESENT("정상 제출"),
    UPDATED("다시 제출"),
    LATE("지각 제출"),
    ABSENT("미제출");

    @Getter
    private final String value;

    AttendanceStatus(String value) {
        this.value = value;
    }
}
