package com.letsintern.letsintern.domain.review.dto.response;

import com.letsintern.letsintern.domain.review.domian.Review;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class ReviewListResponse {

    private List<Review> reviewList;
    private PageInfo pageInfo;

    @Builder
    private ReviewListResponse(Page<Review> reviewList) {
        if(reviewList.hasContent()) this.reviewList = reviewList.getContent();
        this.pageInfo = PageInfo.of(reviewList);
    }

    public static ReviewListResponse from(Page<Review> reviewList) {
        return ReviewListResponse.builder()
                .reviewList(reviewList)
                .build();
    }
}
