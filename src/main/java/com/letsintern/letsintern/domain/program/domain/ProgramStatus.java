package com.letsintern.letsintern.domain.program.domain;

public enum ProgramStatus {

    OPEN("모집중"),
    CLOSED("모집완료"),
    SELECTED("선발완료"),
    DONE("진행완료");

    ProgramStatus(String status) {

    }
}
