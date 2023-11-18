package com.letsintern.letsintern.domain.review.dto.response;

import com.letsintern.letsintern.domain.review.domian.Review;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ReviewListResponse {

    private List<Review> reviewList;

    @Builder
    private ReviewListResponse(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public static ReviewListResponse from(List<Review> reviewList) {
        return ReviewListResponse.builder()
                .reviewList(reviewList)
                .build();
    }
}
