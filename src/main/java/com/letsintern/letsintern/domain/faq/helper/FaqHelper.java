package com.letsintern.letsintern.domain.faq.helper;

import com.letsintern.letsintern.domain.faq.domain.Faq;
import com.letsintern.letsintern.domain.faq.dto.request.FaqCreateDTO;
import com.letsintern.letsintern.domain.faq.dto.request.FaqUpdateDTO;
import com.letsintern.letsintern.domain.faq.exception.FaqNotFound;
import com.letsintern.letsintern.domain.faq.repository.FaqRepository;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FaqHelper {

    private final FaqRepository faqRepository;

    public FaqVo createFaq(ProgramType programType, FaqCreateDTO faqCreateDTO) {
        Faq newFaq = Faq.of(programType, faqCreateDTO.getQuestion(), faqCreateDTO.getAnswer());
        Long savedFaqId = faqRepository.save(newFaq).getId();

        return faqRepository.findVoById(savedFaqId);
    }

    public Long updateFaq(Long faqId, FaqUpdateDTO faqUpdateDTO) {
        Faq faq = faqRepository.findById(faqId)
                .orElseThrow(() ->{
                    throw FaqNotFound.EXCEPTION;
                });

        if(faqUpdateDTO.getProgramType() != null) {
            faq.setProgramType(faqUpdateDTO.getProgramType());
        }
        if(faqUpdateDTO.getQuestion() != null) {
            faq.setQuestion(faqUpdateDTO.getQuestion());
        }
        if(faqUpdateDTO.getAnswer() != null) {
            faq.setAnswer((faqUpdateDTO.getAnswer()));
        }

        return faq.getId();
    }

    public List<FaqVo> getProgramFaqList(ProgramType programType) {
        return faqRepository.findVoListByProgramType(programType);
    }

    public void deleteFaq(Long faqId) {
        Faq targetFaq = faqRepository.findById(faqId)
                        .orElseThrow(() -> FaqNotFound.EXCEPTION);

        faqRepository.delete(targetFaq);
    }

    public FaqVo findFaqVoOrThrow(Long faqId) {
        return faqRepository.findVoById(faqId);
    }
}
