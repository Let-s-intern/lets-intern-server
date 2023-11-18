package com.letsintern.letsintern.domain.review.service;

import com.letsintern.letsintern.domain.review.domian.ReviewStatus;
import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import com.letsintern.letsintern.domain.review.dto.request.ReviewUpdateDTO;
import com.letsintern.letsintern.domain.review.dto.response.ReviewIdResponse;
import com.letsintern.letsintern.domain.review.dto.response.ReviewListResponse;
import com.letsintern.letsintern.domain.review.helper.ReviewHelper;
import com.letsintern.letsintern.domain.review.mapper.ReviewMapper;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewHelper reviewHelper;
    private final ReviewMapper reviewMapper;

    public ReviewIdResponse createReview(Long applicationId, ReviewCreateDTO reviewCreateDTO, PrincipalDetails principalDetails) {
        if(principalDetails != null) {
            return reviewMapper.toReviewIdResponse(reviewHelper.createReview(applicationId, reviewCreateDTO, principalDetails.getUsername()));
        }
        return reviewMapper.toReviewIdResponse(reviewHelper.createReview(applicationId, reviewCreateDTO, null));
    }

    public ReviewListResponse getReviewListOfProgram(Long programId, Pageable pageable) {
        return reviewMapper.toReviewListResponseDTO(reviewHelper.getReviewListOfProgram(programId, pageable));
    }

    public ReviewIdResponse updateReviewStatus(Long reviewId, ReviewUpdateDTO reviewUpdateDTO) {
        return reviewMapper.toReviewIdResponse(reviewHelper.updateReviewStatus(reviewId, reviewUpdateDTO));
    }
}
