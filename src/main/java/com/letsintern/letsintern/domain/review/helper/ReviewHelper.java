package com.letsintern.letsintern.domain.review.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.review.domian.Review;
import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import com.letsintern.letsintern.domain.review.dto.request.ReviewUpdateDTO;
import com.letsintern.letsintern.domain.review.exception.ReviewNotFound;
import com.letsintern.letsintern.domain.review.exception.ReviewUnAuthorized;
import com.letsintern.letsintern.domain.review.mapper.ReviewMapper;
import com.letsintern.letsintern.domain.review.repository.ReviewRepository;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewHelper {
    private final ApplicationRepository applicationRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public Long createReview(Long applicationId, ReviewCreateDTO reviewCreateDTO, String username) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> ApplicationNotFound.EXCEPTION);

        if (!application.getIsApproved()) {
            throw ReviewUnAuthorized.EXCEPTION;
        }

        Review newReview = reviewMapper.toEntity(application.getProgram(), reviewCreateDTO, username);
        Long reviewId = reviewRepository.save(newReview).getId();
        application.setReviewId(reviewId);

        return reviewId;
    }

    public Page<Review> getReviewListOfProgram(Long programId, Pageable pageable) {
        return reviewRepository.findAllByProgramId(programId, pageable);
    }

    public Long updateReviewStatus(Long reviewId, ReviewUpdateDTO reviewUpdateDTO) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFound.EXCEPTION);

        if (reviewUpdateDTO.getStatus() != null) {
            review.setStatus(reviewUpdateDTO.getStatus());
        }

        return review.getId();
    }

    public ReviewVo getReview(Long reviewId) {
        ReviewVo reviewVo = reviewRepository.findVoReviewId(reviewId)
                .orElseThrow(() -> {
                    throw ReviewNotFound.EXCEPTION;
                });
        return reviewVo;
    }

    public List<ReviewVo> findAllVosByProgramType(ProgramType programType) {
        return reviewRepository.findAllVosByProgramType(programType);
    }

    public Review findReviewOrThrow(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFound.EXCEPTION);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }
}
