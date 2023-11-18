package com.letsintern.letsintern.domain.review.exception;

import com.letsintern.letsintern.global.error.BaseErrorException;

public class ReviewUnAuthorized extends BaseErrorException {

    public static final ReviewUnAuthorized EXCEPTION = new ReviewUnAuthorized();

    private ReviewUnAuthorized() {
        super(ReviewErrorCode.REVIEW_UNAUTHORIZED);
    }
}
