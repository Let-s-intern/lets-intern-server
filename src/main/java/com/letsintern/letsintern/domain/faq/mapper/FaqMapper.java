package com.letsintern.letsintern.domain.faq.mapper;

import com.letsintern.letsintern.domain.faq.dto.response.FaqIdResponse;
import org.springframework.stereotype.Component;

@Component
public class FaqMapper {

    public FaqIdResponse toFaqIdResponse(Long faqId) {
        return FaqIdResponse.from(faqId);
    }
}
