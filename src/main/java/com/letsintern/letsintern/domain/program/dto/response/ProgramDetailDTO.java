package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.faq.domain.Faq;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProgramDetailDTO {

    private ProgramDetailVo programDetailVo;
    private List<Faq> faqList;

    @Builder
    private ProgramDetailDTO(ProgramDetailVo programDetailVo, List<Faq> faqList) {
        this.programDetailVo = programDetailVo;
        this.faqList = faqList;
    }

    public static ProgramDetailDTO of(ProgramDetailVo programDetailVo, List<Faq> faqList) {
        return ProgramDetailDTO.builder()
                .programDetailVo(programDetailVo)
                .faqList(faqList)
                .build();
    }
}
