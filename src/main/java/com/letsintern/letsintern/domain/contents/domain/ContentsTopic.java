package com.letsintern.letsintern.domain.contents.domain;

import lombok.Getter;

public enum ContentsTopic {

    EXPERIENCE("경험정리"),
    JOB("직무탐색"),
    CONCEPT("컨셉잡기"),
    DOCUMENT("서류작성"),
    RECRUITMENT("공고분석"),
    APPLY("지원하기"),
    NULL("없음");

    @Getter
    private final String value;

    ContentsTopic(String value) {
        this.value = value;
    }
}
