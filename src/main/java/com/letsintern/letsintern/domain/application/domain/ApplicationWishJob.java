package com.letsintern.letsintern.domain.application.domain;

import com.letsintern.letsintern.domain.program.domain.ChallengeTopic;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

public enum ApplicationWishJob {

    ALL("전체", ChallengeTopic.ALL),

    MANAGEMENT_ALL("경영관리 전반", ChallengeTopic.MANAGEMENT),
    MANAGEMENT_HR("인사(HRD, HRM)", ChallengeTopic.MANAGEMENT),
    MANAGEMENT_GENERAL("총무", ChallengeTopic.MANAGEMENT),
    MANAGEMENT_FINANCE("회계/재무/자금", ChallengeTopic.MANAGEMENT),

    FINANCE_ALL("금융 전반", ChallengeTopic.FINANCE),

    MARKETING_ALL("마케팅 전반", ChallengeTopic.MARKETING),
    MARKETING_BRAND("브랜드 마케팅", ChallengeTopic.MARKETING),
    MARKETING_PERFORMANCE("퍼포먼스 마케팅", ChallengeTopic.MARKETING),
    MARKETING_CRM("CRM 마케팅", ChallengeTopic.MARKETING),
    MARKETING_CONTENTS("콘텐츠 마케팅", ChallengeTopic.MARKETING),

    AD_ALL("광고 전반", ChallengeTopic.AD),
    AD_PR("홍보 PR", ChallengeTopic.AD),
    AD_AE("광고대행사 (AE)", ChallengeTopic.AD),

    DESIGN_ALL("디자인 전반", ChallengeTopic.DESIGN),
    DESIGN_GRAPHIC("그래픽 디자인", ChallengeTopic.DESIGN),
    DESIGN_UIUX("UI/UX 디자인", ChallengeTopic.DESIGN),

    BROADCASTING_ALL("방송 전반", ChallengeTopic.BROADCASTING),
    BROADCASTING_PD("PD", ChallengeTopic.BROADCASTING),
    BROADCASTING_WRITER("작가", ChallengeTopic.BROADCASTING),
    BROADCASTING_PRESS("언론(아나운서, 기자)", ChallengeTopic.BROADCASTING),

    DEVELOPMENT_ALL("개발 전반", ChallengeTopic.DEVELOPMENT),
    DEVELOPMENT_FRONTEND("프론트엔드 개발", ChallengeTopic.DEVELOPMENT),
    DEVELOPMENT_BACKEND("백엔드 개발", ChallengeTopic.DEVELOPMENT),
    DEVELOPMENT_APP("앱개발", ChallengeTopic.DEVELOPMENT),
    DEVELOPMENT_DATA("데이터 분석", ChallengeTopic.DEVELOPMENT),
    DEVELOPMENT_AI("인공지능/머신러닝", ChallengeTopic.DEVELOPMENT),

    SALES_ALL("영업 전반", ChallengeTopic.SALES),
    SALES_MANAGEMENT("영업관리", ChallengeTopic.SALES),
    SALES_OVERSEA("해외영업", ChallengeTopic.SALES),

    SERVICE_PLANNING_ALL("서비스 기획 전반", ChallengeTopic.SERVICE_PLANNING),
    SERVICE_PLANNING_PM("PM", ChallengeTopic.SERVICE_PLANNING),
    SERVICE_PLANNING_PO("PO", ChallengeTopic.SERVICE_PLANNING),

    BUSINESS_ALL("사업/전략 전반", ChallengeTopic.BUSINESS),
    BUSINESS_DEVELOPMENT("사업개발", ChallengeTopic.BUSINESS),
    BUSINESS_STRATEGY("전략기획", ChallengeTopic.BUSINESS),

    CONSULTING_ALL("컨설팅 전반", ChallengeTopic.CONSULTING),

    DISTRIBUTION_ALL("유통 전반", ChallengeTopic.DISTRIBUTION),
    DISTRIBUTION_MD("상품 기획", ChallengeTopic.DISTRIBUTION),
    DISTRIBUTION_MANAGEMENT("물류관리(구매, 유통)", ChallengeTopic.DISTRIBUTION),

    RESEARCH_ALL("공정 및 연구 전반", ChallengeTopic.RESEARCH),
    RESEARCH_PROCESS("공정 관리(생산, 품질)", ChallengeTopic.RESEARCH),
    RESEARCH_ELEC("엔지니어(전기/전자, 반도체)", ChallengeTopic.RESEARCH),
    RESEARCH_MACHINE("엔지니어(기계)", ChallengeTopic.RESEARCH),
    RESEARCH_BIO("엔지니어(화학, 바이오)", ChallengeTopic.RESEARCH),
    RESEARCH_RND("R&D 연구원", ChallengeTopic.RESEARCH)

    ;

    @Getter
    private final String value;

    @Getter
    private final ChallengeTopic challengeTopic;

    ApplicationWishJob(String value, ChallengeTopic challengeTopic) {
        this.value = value;
        this.challengeTopic = challengeTopic;
    }

    public static List<ApplicationWishJob> getApplicationWishJobListByProgramTopic(ChallengeTopic challengeTopic) {
        if(challengeTopic == null || challengeTopic.equals(ChallengeTopic.ALL)) {
            return Stream.of(ApplicationWishJob.values()).toList();
        }

        return Stream.of(ApplicationWishJob.values())
                .filter(wishJob -> wishJob.getChallengeTopic().equals(challengeTopic))
                .toList();
    }
}
