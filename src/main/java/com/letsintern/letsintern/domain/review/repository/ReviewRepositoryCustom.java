package com.letsintern.letsintern.domain.review.repository;

import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepositoryCustom {

    List<ReviewVo> findAllByProgramId(Long programId, Pageable pageable);
}
