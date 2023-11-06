package com.letsintern.letsintern.domain.review.repository;

import com.letsintern.letsintern.domain.review.domian.QReview;
import com.letsintern.letsintern.domain.review.vo.QReviewVo;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ReviewVo> findAllByProgramId(Long programId, Pageable pageable) {
        QReview qReview = QReview.review;

        return jpaQueryFactory
                .select(new QReviewVo(
                        qReview.id,
                        qReview.grade,
                        qReview.reviewContents,
                        qReview.suggestContents,
                        qReview.status
                ))
                .from(qReview)
                .where(qReview.programId.eq(programId))
                .orderBy(qReview.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
