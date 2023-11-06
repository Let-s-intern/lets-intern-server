package com.letsintern.letsintern.domain.review.vo;

import com.letsintern.letsintern.domain.review.domian.ReviewStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class ReviewVo {

    private Long id;

    private Integer grade;

    private String reviewContents;

    private String suggestContents;

    private ReviewStatus status;

    @QueryProjection
    public ReviewVo(Long id, Integer grade, String reviewContents, String suggestContents, ReviewStatus status) {
        this.id = id;
        this.grade = grade;
        this.reviewContents = reviewContents;
        this.suggestContents = suggestContents;
        this.status = status;
    }
}
