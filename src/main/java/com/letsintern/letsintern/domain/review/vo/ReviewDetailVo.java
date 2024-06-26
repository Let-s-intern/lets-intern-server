package com.letsintern.letsintern.domain.review.vo;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.review.domian.Review;
import com.letsintern.letsintern.global.common.util.StringUtils;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewDetailVo {

    private Long id;

    private String username;

    private Integer grade;

    private String reviewContents;

    private String createdAt;

    private ProgramType programType;
    private String programTitle;
    private String startDate;
    private String endDate;

    @Builder
    public ReviewDetailVo(Long id, String username, Integer grade, String reviewContents, String createdAt,
                          ProgramType programType, String programTitle, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.username = username;
        this.grade = grade;
        this.reviewContents = reviewContents;
        this.createdAt = createdAt;
        this.programType = programType;
        this.programTitle = programTitle;
        this.startDate = StringUtils.dateToString(startDate);
        this.endDate = StringUtils.dateToString(endDate);
    }

    public static ReviewDetailVo of(Review review, Program program) {
        return ReviewDetailVo.builder()
                .id(review.getId())
                .username(review.getUserName())
                .grade(review.getGrade())
                .reviewContents(review.getReviewContents())
                .createdAt(review.getCreatedAt())
                .programType(review.getProgramType())
                .programTitle(program.getTitle())
                .startDate(program.getStartDate())
                .endDate(program.getEndDate())
                .build();
    }
}
