package com.letsintern.letsintern.domain.review.vo;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.letsintern.letsintern.domain.review.vo.QReviewVo is a Querydsl Projection type for ReviewVo
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReviewVo extends ConstructorExpression<ReviewVo> {

    private static final long serialVersionUID = -326546483L;

    public QReviewVo(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<Integer> grade, com.querydsl.core.types.Expression<String> reviewContents, com.querydsl.core.types.Expression<String> suggestContents, com.querydsl.core.types.Expression<com.letsintern.letsintern.domain.review.domian.ReviewStatus> status) {
        super(ReviewVo.class, new Class<?>[]{long.class, int.class, String.class, String.class, com.letsintern.letsintern.domain.review.domian.ReviewStatus.class}, id, grade, reviewContents, suggestContents, status);
    }

}

