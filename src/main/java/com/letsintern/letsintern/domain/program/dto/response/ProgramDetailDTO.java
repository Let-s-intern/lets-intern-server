package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.faq.domain.Faq;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProgramDetailDTO {

    private ProgramDetailVo programDetailVo;
    private List<Faq> faqList;
    private List<ReviewVo> reviewList;

    @Builder
    private ProgramDetailDTO(ProgramDetailVo programDetailVo, List<Faq> faqList, List<ReviewVo> reviewList) {
        this.programDetailVo = programDetailVo;
        this.faqList = faqList;
        this.reviewList = reviewList;
    }

    public static ProgramDetailDTO of(ProgramDetailVo programDetailVo, List<Faq> faqList, List<ReviewVo> reviewList) {
        return ProgramDetailDTO.builder()
                .programDetailVo(programDetailVo)
                .faqList(faqList)
                .reviewList(reviewList)
                .build();
    }
}
