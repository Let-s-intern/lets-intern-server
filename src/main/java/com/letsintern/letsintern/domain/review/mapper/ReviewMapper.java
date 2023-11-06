package com.letsintern.letsintern.domain.review.mapper;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.review.domian.Review;
import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import com.letsintern.letsintern.domain.review.dto.response.ReviewIdResponseDTO;
import com.letsintern.letsintern.domain.review.dto.response.ReviewListResponseDTO;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    public Review toEntity(Application application, ReviewCreateDTO reviewCreateDTO) {
        return Review.of(reviewCreateDTO, application.getProgram().getId());
    }

    public ReviewIdResponseDTO toReviewIdResponse(Long reviewId) {
        return ReviewIdResponseDTO.of(reviewId);
    }

    public ReviewListResponseDTO toReviewListResponseDTO(List<ReviewVo> reviewVoList) {
        return ReviewListResponseDTO.from(reviewVoList);
    }
}
