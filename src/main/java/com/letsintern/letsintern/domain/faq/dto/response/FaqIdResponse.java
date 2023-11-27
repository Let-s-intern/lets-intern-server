package com.letsintern.letsintern.domain.faq.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FaqIdResponse {

    private Long faqId;

    @Builder
    private FaqIdResponse(Long faqId) {
        this.faqId = faqId;
    }

    public static FaqIdResponse from(Long faqId) {
        return FaqIdResponse.builder()
                .faqId(faqId)
                .build();
    }
}
