package com.letsintern.letsintern.domain.faq.mapper;

import com.letsintern.letsintern.domain.faq.dto.response.FaqIdResponse;
import com.letsintern.letsintern.domain.faq.dto.response.FaqVoListResponse;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FaqMapper {

    public FaqIdResponse toFaqIdResponse(Long faqId) {
        return FaqIdResponse.from(faqId);
    }

    public FaqVoListResponse toFaqVoListResponse(List<FaqVo> faqList) {
        return FaqVoListResponse.of(faqList);
    }
}
