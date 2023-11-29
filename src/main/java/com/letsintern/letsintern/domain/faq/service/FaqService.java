package com.letsintern.letsintern.domain.faq.service;

import com.letsintern.letsintern.domain.faq.dto.request.FaqCreateDTO;
import com.letsintern.letsintern.domain.faq.dto.request.FaqUpdateDTO;
import com.letsintern.letsintern.domain.faq.dto.response.FaqIdResponse;
import com.letsintern.letsintern.domain.faq.dto.response.FaqVoListResponse;
import com.letsintern.letsintern.domain.faq.helper.FaqHelper;
import com.letsintern.letsintern.domain.faq.mapper.FaqMapper;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqHelper faqHelper;
    private final FaqMapper faqMapper;

    @Transactional
    public FaqVo createFaq(ProgramType programType, FaqCreateDTO faqCreateDTO) {
        return faqHelper.createFaq(programType, faqCreateDTO);
    }

    @Transactional
    public FaqIdResponse updateFaq(Long faqId, FaqUpdateDTO faqUpdateDTO) {
        return faqMapper.toFaqIdResponse(faqHelper.updateFaq(faqId, faqUpdateDTO));
    }

    public FaqVoListResponse getProgramFaqList(ProgramType programType) {
        return faqMapper.toFaqVoListResponse(faqHelper.getProgramFaqList(programType));
    }

    @Transactional
    public void deleteFaq(Long faqId) {
        faqHelper.deleteFaq(faqId);
    }
}
