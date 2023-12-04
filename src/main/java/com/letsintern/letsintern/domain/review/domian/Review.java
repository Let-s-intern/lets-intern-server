package com.letsintern.letsintern.domain.review.domian;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter @Setter
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

    @NotNull
    ProgramType programType;

    @NotNull
    Integer programTh;

    @Nullable
    String userName;

    @NotNull
    @Column(length = 15)
    String createdAt;

    @NotNull
    ReviewStatus status = ReviewStatus.INVISIBLE;

    @Builder
    private Review(Integer grade, String reviewContents, String suggestContents, Long programId, ProgramType programType, Integer programTh, String username) {
        this.grade = grade;
        this.reviewContents = reviewContents;
        this.suggestContents = suggestContents;
        this.programId = programId;
        this.programType = programType;
        this.programTh = programTh;
        if(username != null) this.userName = username;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        this.createdAt = simpleDateFormat.format(new Date());
    }

    public static Review of(ReviewCreateDTO reviewCreateDTO, Long programId, ProgramType programType, Integer programTh, String username) {
        return Review.builder()
                .grade(reviewCreateDTO.getGrade())
                .reviewContents(reviewCreateDTO.getReviewContents())
                .suggestContents(reviewCreateDTO.getSuggestContents())
                .programId(programId)
                .programType(programType)
                .programTh(programTh)
                .username(username)
                .build();
    }
}
