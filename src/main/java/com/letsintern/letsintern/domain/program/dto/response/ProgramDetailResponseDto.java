package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;

import java.util.List;

public record ProgramDetailResponseDto<T>(
        T programInfo,
        boolean participated,
        List<FaqVo> faqList,
        List<ReviewVo> reviewList,
        List<ApplicationWishJob> wishJobList
) {
    public static <T> ProgramDetailResponseDto<?> of(T programDetailVo,
                                                     boolean participated,
                                                     List<FaqVo> faqList,
                                                     List<ReviewVo> reviewList,
                                                     List<ApplicationWishJob> wishJobList) {
        return new ProgramDetailResponseDto<>(programDetailVo, participated, faqList, reviewList, wishJobList);
    }
}
