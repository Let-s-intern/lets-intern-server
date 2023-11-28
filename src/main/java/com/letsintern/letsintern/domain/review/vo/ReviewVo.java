package com.letsintern.letsintern.domain.review.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewVo {

    private Long id;

    private String username;

    private Integer grade;

    private String reviewContents;

    private String createdAt;


    @Builder
    public ReviewVo(Long id, String username, Integer grade, String reviewContents, String createdAt) {
        this.id = id;
        this.username = username;
        this.grade = grade;
        this.reviewContents = reviewContents;
        this.createdAt = createdAt;
    }
}
