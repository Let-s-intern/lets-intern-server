package com.letsintern.letsintern.domain.review.mapper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.review.domian.Review;
import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import com.letsintern.letsintern.domain.review.dto.response.ReviewIdResponse;
import com.letsintern.letsintern.domain.review.dto.response.ReviewListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    public Review toEntity(Application application, ReviewCreateDTO reviewCreateDTO, String username) {
        return Review.of(reviewCreateDTO, application.getProgram().getId(), username);
    }

    public ReviewIdResponse toReviewIdResponse(Long reviewId) {
        return ReviewIdResponse.of(reviewId);
    }

    public ReviewListResponse toReviewListResponseDTO(List<Review> reviewList) {
        return ReviewListResponse.from(reviewList);
    }
}
