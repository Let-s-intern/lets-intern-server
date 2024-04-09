package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.global.utils.EnumField;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ChallengeTopic implements EnumField {
    ALL(1, "전체"),
    MANAGEMENT(2, "경영관리"),
    FINANCE(3, "금융"),
    MARKETING(4, "마케팅"),
    AD(5, "광고"),
    DESIGN(6, "디자인"),
    BROADCASTING(7, "방송"),
    DEVELOPMENT(8, "개발"),
    SALES(9, "영업"),
    SERVICE_PLANNING(10, "서비스 기획"),
    BUSINESS(11, "사업/전략"),
    CONSULTING(12, "컨설팅"),
    DISTRIBUTION(13, "유통"),
    RESEARCH(14, "공정 및 연구");

    private final Integer code;
    private final String desc;
}
