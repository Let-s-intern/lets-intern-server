package com.letsintern.letsintern.domain.attendance.domain;

import lombok.Getter;

public enum AttendanceStatus {

    CREATED("확인중"),
    PASSED("확인 완료"),
    WRONG("반려"),
    UPDATED("수정됨"),
    LATE("지각"),
    ABSENT("미제출");

    @Getter
    private final String value;

    AttendanceStatus(String value) {
        this.value = value;
    }
}
