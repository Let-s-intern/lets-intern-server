package com.letsintern.letsintern.domain.review.service;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.helper.ProgramHelper;
import com.letsintern.letsintern.domain.review.domian.Review;
import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import com.letsintern.letsintern.domain.review.dto.request.ReviewUpdateDTO;
import com.letsintern.letsintern.domain.review.dto.response.ReviewIdResponse;
import com.letsintern.letsintern.domain.review.dto.response.ReviewListResponse;
import com.letsintern.letsintern.domain.review.helper.ReviewHelper;
import com.letsintern.letsintern.domain.review.mapper.ReviewMapper;
import com.letsintern.letsintern.domain.review.vo.ReviewDetailVo;
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
    private final ProgramHelper programHelper;

    public ReviewIdResponse createLinkReview(Long programId, ReviewCreateDTO reviewCreateDTO) {
        Program program = programHelper.findProgramOrThrow(programId);
        Review newReview = createReviewAndSave(program, reviewCreateDTO);
        return reviewMapper.toReviewIdResponse(newReview.getId());
    }

    public ReviewIdResponse createReview(Long applicationId, ReviewCreateDTO reviewCreateDTO, PrincipalDetails principalDetails) {
        if (principalDetails != null) {  // 회원 리뷰
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

    public ReviewDetailVo getReviewDetail(Long reviewId) {
        Review review = reviewHelper.findReviewOrThrow(reviewId);
        Program program = programHelper.findProgramOrThrow(review.getProgramId());
        return reviewMapper.toReviewDetailVo(review, program);
    }

    private Review createReviewAndSave(Program program, ReviewCreateDTO reviewCreateDTO) {
        Review newReview = reviewMapper.toEntity(program, reviewCreateDTO, null);
        return reviewHelper.saveReview(newReview);
    }
}
