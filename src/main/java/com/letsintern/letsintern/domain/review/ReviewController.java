package com.letsintern.letsintern.domain.review;

import com.letsintern.letsintern.domain.review.domian.ReviewStatus;
import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import com.letsintern.letsintern.domain.review.dto.response.ReviewIdResponseDTO;
import com.letsintern.letsintern.domain.review.dto.response.ReviewListResponseDTO;
import com.letsintern.letsintern.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Tag(name = "Review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create/{applicationId}")
    @Operation(summary = "리뷰 생성")
    public ReviewIdResponseDTO createReview(@PathVariable Long applicationId, @RequestBody ReviewCreateDTO reviewCreateDTO) {
        return reviewService.createReview(applicationId, reviewCreateDTO);
    }

    @GetMapping("/admin/list/{programId}")
    @Operation(summary = "어드민 프로그램 별 리뷰 목록")
    public ReviewListResponseDTO getReviewListOfProgram(@PathVariable Long programId, @PageableDefault(size = 15) Pageable pageable) {
        return reviewService.getReviewListOfProgram(programId, pageable);
    }

    @GetMapping("/admin/update/{reviewId}/{status}")
    @Operation(summary = "어드민 리뷰 상태 변경")
    public ReviewIdResponseDTO updateReviewStatus(@PathVariable Long reviewId, @PathVariable ReviewStatus status) {
        return reviewService.updateReviewStatus(reviewId, status);
    }
}
