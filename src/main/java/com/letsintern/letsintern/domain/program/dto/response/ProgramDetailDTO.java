package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.faq.domain.Faq;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProgramDetailDTO {

    private ProgramDetailVo programDetailVo;

    private boolean participated;
    private List<FaqVo> faqList;
    private List<ReviewVo> reviewList;

    @Builder
    private ProgramDetailDTO(ProgramDetailVo programDetailVo, boolean participated, List<FaqVo> faqList, List<ReviewVo> reviewList) {
        this.programDetailVo = programDetailVo;
        this.participated = participated;
        this.faqList = faqList;
        this.reviewList = reviewList;
    }

    public static ProgramDetailDTO of(ProgramDetailVo programDetailVo, boolean participated, List<FaqVo> faqList, List<ReviewVo> reviewList) {
        return ProgramDetailDTO.builder()
                .programDetailVo(programDetailVo)
                .participated(participated)
                .faqList(faqList)
                .reviewList(reviewList)
                .build();
    }
}
