package com.letsintern.letsintern.domain.review.repository;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.review.domian.Review;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryCustom {

    Page<Review> findAllByProgramId(Long programId, Pageable pageable);

    List<ReviewVo> findAllVosByProgramType(ProgramType programType);

    Optional<ReviewVo> findVoReviewId(Long reviewId);

    List<String> findAllReviewContentsByProgramId(Long programId);
}
