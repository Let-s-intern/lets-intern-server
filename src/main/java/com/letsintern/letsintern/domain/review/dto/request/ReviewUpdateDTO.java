package com.letsintern.letsintern.domain.review.dto.request;

import com.letsintern.letsintern.domain.review.domian.ReviewStatus;
import lombok.Getter;

@Getter
public class ReviewUpdateDTO {

    private ReviewStatus status;
}
