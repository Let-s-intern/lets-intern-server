package com.letsintern.letsintern.domain.mission.domain;

import lombok.Getter;

public enum MissionTopic {

    EXPERIENCE("경험정리"),
    JOB("직무탐색"),
    CONCEPT("컨셉잡기"),
    DOCUMENT("서류작성"),
    RECRUITMENT("공고분석"),
    APPLY("지원하기");

    @Getter
    private final String value;

    MissionTopic(String value) {
        this.value = value;
    }
}
