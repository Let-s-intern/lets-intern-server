package com.letsintern.letsintern.domain.application.domain;

import com.letsintern.letsintern.domain.program.domain.ProgramTopic;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum ApplicationWishJob {

    ALL("전체", ProgramTopic.ALL),

    MARKETING_ALL("마케팅 전반", ProgramTopic.MARKETING),
    MARKETING_BRAND("브랜드 마케팅", ProgramTopic.MARKETING),
    MARKETING_PERFORMANCE("퍼포먼스 마케팅", ProgramTopic.MARKETING),
    MARKETING_CRM("CRM 마케팅", ProgramTopic.MARKETING),
    MARKETING_CONTENTS("콘텐츠 마케팅", ProgramTopic.MARKETING),
    MARKETING_PR("홍보 PR", ProgramTopic.MARKETING),
    MARKETING_AE("광고대행사 (AE)", ProgramTopic.MARKETING),

    DEVELOPMENT_ALL("개발 전반", ProgramTopic.DEVELOPMENT),
    DEVELOPMENT_FRONTEND("프론트엔드", ProgramTopic.DEVELOPMENT),
    DEVELOPMENT_BACKEND("백엔드", ProgramTopic.DEVELOPMENT),
    DEVELOPMENT_APP("앱개발", ProgramTopic.DEVELOPMENT),
    DEVELOPMENT_DATA("데이터 분석", ProgramTopic.DEVELOPMENT),
    DEVELOPMENT_AI("인공지능/머신러닝", ProgramTopic.DEVELOPMENT);

    @Getter
    private final String value;

    @Getter
    private final ProgramTopic programTopic;

    ApplicationWishJob(String value, ProgramTopic programTopic) {
        this.value = value;
        this.programTopic = programTopic;
    }

    public static List<ApplicationWishJob> getApplicationWishJobListByProgramTopic(ProgramTopic programTopic) {
        return Stream.of(ApplicationWishJob.values())
                .filter(wishJob -> wishJob.getProgramTopic().equals(programTopic))
                .toList();
    }
}
