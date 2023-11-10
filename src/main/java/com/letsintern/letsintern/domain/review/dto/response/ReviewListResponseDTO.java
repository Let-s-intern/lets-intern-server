package com.letsintern.letsintern.domain.review.dto.response;

import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ReviewListResponseDTO {

    private List<ReviewVo> reviewList;

    @Builder
    private ReviewListResponseDTO(List<ReviewVo> reviewList) {
        this.reviewList = reviewList;
    }

    public static ReviewListResponseDTO from(List<ReviewVo> reviewList) {
        return ReviewListResponseDTO.builder()
                .reviewList(reviewList)
                .build();
    }
}
