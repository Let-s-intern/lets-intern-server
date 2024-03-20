package com.letsintern.letsintern.domain.application.domain;

import com.letsintern.letsintern.domain.program.domain.ProgramTopic;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public enum ApplicationWishJob {

    ALL("전체", ProgramTopic.ALL),

    MANAGEMENT_ALL("경영관리 전반", ProgramTopic.MANAGEMENT),
    MANAGEMENT_HR("인사(HRD, HRM)", ProgramTopic.MANAGEMENT),
    MANAGEMENT_GENERAL("총무", ProgramTopic.MANAGEMENT),
    MANAGEMENT_FINANCE("회계/재무/자금", ProgramTopic.MANAGEMENT),

    FINANCE_ALL("금융 전반", ProgramTopic.FINANCE),

    MARKETING_ALL("마케팅 전반", ProgramTopic.MARKETING),
    MARKETING_BRAND("브랜드 마케팅", ProgramTopic.MARKETING),
    MARKETING_PERFORMANCE("퍼포먼스 마케팅", ProgramTopic.MARKETING),
    MARKETING_CRM("CRM 마케팅", ProgramTopic.MARKETING),
    MARKETING_CONTENTS("콘텐츠 마케팅", ProgramTopic.MARKETING),
    MARKETING_PR("홍보 PR", ProgramTopic.MARKETING),
    MARKETING_AE("광고대행사(AE)", ProgramTopic.MARKETING),

    AD_ALL("광고 전반", ProgramTopic.AD),
    AD_PR("홍보 PR", ProgramTopic.AD),
    AD_AE("광고대행사 (AE)", ProgramTopic.AD),

    DESIGN_ALL("디자인 전반", ProgramTopic.DESIGN),
    DESIGN_GRAPHIC("그래픽 디자인", ProgramTopic.DESIGN),
    DESIGN_UIUX("UI/UX 디자인", ProgramTopic.DESIGN),

    BROADCASTING_ALL("방송 전반", ProgramTopic.BROADCASTING),
    BROADCASTING_PD("PD", ProgramTopic.BROADCASTING),
    BROADCASTING_WRITER("작가", ProgramTopic.BROADCASTING),
    BROADCASTING_PRESS("언론(아나운서, 기자)", ProgramTopic.BROADCASTING),

    DEVELOPMENT_ALL("개발 전반", ProgramTopic.DEVELOPMENT),
    DEVELOPMENT_FRONTEND("프론트엔드 개발", ProgramTopic.DEVELOPMENT),
    DEVELOPMENT_BACKEND("백엔드 개발", ProgramTopic.DEVELOPMENT),
    DEVELOPMENT_APP("앱개발", ProgramTopic.DEVELOPMENT),
    DEVELOPMENT_DATA("데이터 분석", ProgramTopic.DEVELOPMENT),
    DEVELOPMENT_AI("인공지능/머신러닝", ProgramTopic.DEVELOPMENT),

    SALES_ALL("영업 전반", ProgramTopic.SALES),
    SALES_MANAGEMENT("영업관리", ProgramTopic.SALES),
    SALES_OVERSEA("해외영업", ProgramTopic.SALES),

    SERVICE_PLANNING_ALL("서비스 기획 전반", ProgramTopic.SERVICE_PLANNING),
    SERVICE_PLANNING_PM("PM", ProgramTopic.SERVICE_PLANNING),
    SERVICE_PLANNING_PO("PO", ProgramTopic.SERVICE_PLANNING),

    BUSINESS_ALL("사업/전략 전반", ProgramTopic.BUSINESS),
    BUSINESS_DEVELOPMENT("사업개발", ProgramTopic.BUSINESS),
    BUSINESS_STRATEGY("전략기획", ProgramTopic.BUSINESS),

    CONSULTING_ALL("컨설팅 전반", ProgramTopic.CONSULTING),

    DISTRIBUTION_ALL("유통 전반", ProgramTopic.DISTRIBUTION),
    DISTRIBUTION_MD("상품 기획", ProgramTopic.DISTRIBUTION),
    DISTRIBUTION_MANAGEMENT("물류관리(구매, 유통)", ProgramTopic.DISTRIBUTION),

    RESEARCH_ALL("공정 및 연구 전반", ProgramTopic.RESEARCH),
    RESEARCH_PROCESS("공정 관리(생산, 품질)", ProgramTopic.RESEARCH),
    RESEARCH_ELEC("엔지니어(전기/전자, 반도체)", ProgramTopic.RESEARCH),
    RESEARCH_MACHINE("엔지니어(기계)", ProgramTopic.RESEARCH),
    RESEARCH_BIO("엔지니어(화학, 바이오)", ProgramTopic.RESEARCH),
    RESEARCH_RND("R&D 연구원", ProgramTopic.RESEARCH)

    ;

    @Getter
    private final String value;

    @Getter
    private final ProgramTopic programTopic;

    ApplicationWishJob(String value, ProgramTopic programTopic) {
        this.value = value;
        this.programTopic = programTopic;
    }

    public static List<ApplicationWishJob> getApplicationWishJobListByProgramTopic(ProgramTopic programTopic) {
        if(programTopic == null || programTopic.equals(ProgramTopic.ALL)) {
            return Stream.of(ApplicationWishJob.values()).toList();
        }

        return Stream.of(ApplicationWishJob.values())
                .filter(wishJob -> wishJob.getProgramTopic().equals(programTopic))
                .toList();
    }
}
