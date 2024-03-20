package com.letsintern.letsintern.domain.program.domain;

import lombok.Getter;

public enum ProgramTopic {

    ALL("전체"),
    MANAGEMENT("경영관리"),
    FINANCE("금융"),
    MARKETING("마케팅"),
    AD("광고"),
    DESIGN("디자인"),
    BROADCASTING("방송"),
    DEVELOPMENT("개발"),
    SALES("영업"),
    SERVICE_PLANNING("서비스 기획"),
    BUSINESS("사업/전략"),
    CONSULTING("컨설팅"),
    DISTRIBUTION("유통"),
    RESEARCH("공정 및 연구");

    @Getter
    private final String value;

    ProgramTopic(String value) {
        this.value = value;
    }
}
