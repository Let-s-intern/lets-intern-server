package com.letsintern.letsintern.domain.review.helper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.exception.ApplicationNotFound;
import com.letsintern.letsintern.domain.application.repository.ApplicationRepository;
import com.letsintern.letsintern.domain.review.domian.Review;
import com.letsintern.letsintern.domain.review.domian.ReviewStatus;
import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import com.letsintern.letsintern.domain.review.exception.ReviewNotFound;
import com.letsintern.letsintern.domain.review.mapper.ReviewMapper;
import com.letsintern.letsintern.domain.review.repository.ReviewRepository;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewHelper {

    private final ApplicationRepository applicationRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public Long createReview(Long applicationId, ReviewCreateDTO reviewCreateDTO) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> ApplicationNotFound.EXCEPTION);

        Review newReview = reviewMapper.toEntity(application, reviewCreateDTO);
        Long reviewId = reviewRepository.save(newReview).getId();
        application.setReviewId(reviewId);

        return reviewId;
    }

    public List<ReviewVo> getReviewListOfProgram(Long programId, Pageable pageable) {
        return reviewRepository.findAllByProgramId(programId, pageable);
    }

    public Long updateReviewStatus(Long reviewId, ReviewStatus status) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> ReviewNotFound.EXCEPTION);

        review.setStatus(status);
        return review.getId();
    }

}
