package com.letsintern.letsintern.domain.review.mapper;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.review.domian.Review;
import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import com.letsintern.letsintern.domain.review.dto.response.ReviewIdResponse;
import com.letsintern.letsintern.domain.review.dto.response.ReviewListResponse;
import com.letsintern.letsintern.domain.review.vo.ReviewDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    public Review toEntity(Long programId, ProgramType programType, Integer programTh, ReviewCreateDTO reviewCreateDTO, String username) {
        return Review.of(reviewCreateDTO, programId, programType, programTh, username);
    }

    public ReviewIdResponse toReviewIdResponse(Long reviewId) {
        return ReviewIdResponse.of(reviewId);
    }

    public ReviewListResponse toReviewListResponseDTO(List<Review> reviewList) {
        return ReviewListResponse.from(reviewList);
    }

    public ReviewDetailVo toReviewDetailVo(Review review, Program program) {
        return ReviewDetailVo.of(review, program);
    }
}
