package com.letsintern.letsintern.domain.review.repository;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.review.domian.Review;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<Review> findAllByProgramId(Long programId, Pageable pageable);
    List<ReviewVo> findAllVosByProgramType(ProgramType programType);
}
