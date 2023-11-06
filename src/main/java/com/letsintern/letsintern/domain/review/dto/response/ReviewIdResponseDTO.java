package com.letsintern.letsintern.domain.review.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewIdResponseDTO {

    private Long reviewId;

    @Builder
    private ReviewIdResponseDTO(Long reviewId) {
        this.reviewId = reviewId;
    }

    public static ReviewIdResponseDTO of(Long reviewId) {
        return ReviewIdResponseDTO.builder()
                .reviewId(reviewId)
                .build();
    }
}
