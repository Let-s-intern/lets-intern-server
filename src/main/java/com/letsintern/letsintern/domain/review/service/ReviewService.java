package com.letsintern.letsintern.domain.review.service;

import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import com.letsintern.letsintern.domain.review.dto.response.ReviewIdResponseDTO;
import com.letsintern.letsintern.domain.review.helper.ReviewHelper;
import com.letsintern.letsintern.domain.review.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewHelper reviewHelper;
    private final ReviewMapper reviewMapper;
    public ReviewIdResponseDTO createReview(Long applicationId, ReviewCreateDTO reviewCreateDTO) {
        return reviewMapper.toReviewIdResponse(reviewHelper.createReview(applicationId, reviewCreateDTO));
    }
}
