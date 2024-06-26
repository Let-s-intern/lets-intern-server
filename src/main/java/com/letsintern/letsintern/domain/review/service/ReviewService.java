package com.letsintern.letsintern.domain.review.service;

import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import com.letsintern.letsintern.domain.review.dto.request.ReviewUpdateDTO;
import com.letsintern.letsintern.domain.review.dto.response.ReviewIdResponse;
import com.letsintern.letsintern.domain.review.dto.response.ReviewListResponse;
import com.letsintern.letsintern.domain.review.helper.ReviewHelper;
import com.letsintern.letsintern.domain.review.mapper.ReviewMapper;
import com.letsintern.letsintern.domain.review.vo.ReviewDetailVo;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
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

    public ReviewIdResponse createLinkReview(Long programId, ReviewCreateDTO reviewCreateDTO) {
        return reviewMapper.toReviewIdResponse(reviewHelper.createLinkReview(programId, reviewCreateDTO));
    }

    public ReviewIdResponse createReview(Long applicationId, ReviewCreateDTO reviewCreateDTO, PrincipalDetails principalDetails) {
        if(principalDetails != null) {  // 회원 리뷰
            return reviewMapper.toReviewIdResponse(reviewHelper.createReview(applicationId, reviewCreateDTO, principalDetails.getUsername()));
        }

        // 비회원 리뷰
        return reviewMapper.toReviewIdResponse(reviewHelper.createReview(applicationId, reviewCreateDTO, null));
    }

    public ReviewListResponse getReviewListOfProgram(Long programId, Pageable pageable) {
        return reviewMapper.toReviewListResponseDTO(reviewHelper.getReviewListOfProgram(programId, pageable));
    }

    public ReviewIdResponse updateReviewStatus(Long reviewId, ReviewUpdateDTO reviewUpdateDTO) {
        return reviewMapper.toReviewIdResponse(reviewHelper.updateReviewStatus(reviewId, reviewUpdateDTO));
    }

    public ReviewVo getReview(Long reviewId) {
        return reviewHelper.getReview(reviewId);
    }

    public ReviewDetailVo getReviewDetail(Long reviewId) {
        return reviewHelper.getReviewDetail(reviewId);
    }
}
