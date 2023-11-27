package com.letsintern.letsintern.domain.faq.dto.response;

import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class FaqVoListResponse {

    private List<FaqVo> faqList;

    @Builder
    private FaqVoListResponse(List<FaqVo> faqList) {
        this.faqList = faqList;
    }

    public static FaqVoListResponse of(List<FaqVo> faqList) {
        return FaqVoListResponse.builder()
                .faqList(faqList)
                .build();
    }
}
