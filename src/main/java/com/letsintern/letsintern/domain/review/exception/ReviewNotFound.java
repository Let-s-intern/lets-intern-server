package com.letsintern.letsintern.domain.review.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ReviewNotFound extends BaseErrorException {

    public static final ReviewNotFound EXCEPTION = new ReviewNotFound();

    private ReviewNotFound() {
        super(ReviewErrorCode.REVIEW_NOT_FOUND);
    }
}
