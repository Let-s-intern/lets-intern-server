package com.letsintern.letsintern.domain.review.repository;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.review.domian.QReview;
import com.letsintern.letsintern.domain.review.domian.Review;
import com.letsintern.letsintern.domain.review.domian.ReviewStatus;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Review> findAllByProgramId(Long programId, Pageable pageable) {
        QReview qReview = QReview.review;

        return jpaQueryFactory
                .selectFrom(qReview)
                .where(qReview.programId.eq(programId))
                .orderBy(qReview.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<ReviewVo> findAllVosByProgramType(ProgramType programType) {
        QReview qReview = QReview.review;

        return jpaQueryFactory
                .select(Projections.constructor(ReviewVo.class,
                    qReview.id,
                    qReview.userName,
                    qReview.grade,
                    qReview.reviewContents,
                    qReview.createdAt
                ))
                .from(qReview)
                .where(qReview.programType.eq(programType), qReview.status.eq(ReviewStatus.VISIBLE))
                .orderBy(qReview.id.desc())
                .fetch();
    }

    @Override
    public Optional<ReviewVo> findVoReviewId(Long reviewId) {
        QReview qReview = QReview.review;

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(ReviewVo.class,
                        qReview.id,
                        qReview.userName,
                        qReview.grade,
                        qReview.reviewContents,
                        qReview.createdAt
                ))
                .from(qReview)
                .where(qReview.id.eq(reviewId))
                .fetchFirst());
    }
}
