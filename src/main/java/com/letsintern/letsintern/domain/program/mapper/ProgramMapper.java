package com.letsintern.letsintern.domain.program.mapper;

import com.letsintern.letsintern.domain.application.domain.ApplicationWishJob;
import com.letsintern.letsintern.domain.faq.vo.FaqVo;
import com.letsintern.letsintern.domain.program.dto.response.ProgramDetailResponseDto;
import com.letsintern.letsintern.domain.program.vo.ProgramDetailVo;
import com.letsintern.letsintern.domain.program.vo.challenge.ChallengeDetailVo;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgramMapper {

    public ProgramDetailResponseDto<?> toProgramDetailResponseDto(ProgramDetailVo programDetailVo,
                                                                  boolean existApplication,
                                                                  List<FaqVo> faqList,
                                                                  List<ReviewVo> reviewList,
                                                                  List<ApplicationWishJob> wishJobList) {
        return ProgramDetailResponseDto.of(programDetailVo, existApplication, faqList, reviewList, wishJobList);
    }
}
