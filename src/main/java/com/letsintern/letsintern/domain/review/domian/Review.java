package com.letsintern.letsintern.domain.review.domian;

import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    Integer grade;

    @NotNull
    String reviewContents;

    @Nullable
    String suggestContents;

    @NotNull
    Long programId;

    @Builder
    private Review(Integer grade, String reviewContents, String suggestContents, Long programId) {
        this.grade = grade;
        this.reviewContents = reviewContents;
        this.suggestContents = suggestContents;
        this.programId = programId;
    }

    public static Review of(ReviewCreateDTO reviewCreateDTO, Long programId) {
        return Review.builder()
                .grade(reviewCreateDTO.getGrade())
                .reviewContents(reviewCreateDTO.getReviewContents())
                .suggestContents(reviewCreateDTO.getSuggestContents())
                .programId(programId)
                .build();
    }
}
