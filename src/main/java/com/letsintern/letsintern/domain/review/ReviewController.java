package com.letsintern.letsintern.domain.review;

import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import com.letsintern.letsintern.domain.review.dto.request.ReviewUpdateDTO;
import com.letsintern.letsintern.domain.review.dto.response.ReviewIdResponse;
import com.letsintern.letsintern.domain.review.dto.response.ReviewListResponse;
import com.letsintern.letsintern.domain.review.service.ReviewService;
import com.letsintern.letsintern.domain.review.vo.ReviewDetailVo;
import com.letsintern.letsintern.domain.review.vo.ReviewVo;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Tag(name = "Review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("")
    @Operation(summary = "리뷰 생성 - 링크용")
    public ReviewIdResponse createLinkReview(
            @RequestParam Long programId,
            @RequestBody ReviewCreateDTO reviewCreateDTO) {
        return reviewService.createLinkReview(programId, reviewCreateDTO);
    }

    @PostMapping("/{applicationId}")
    @Operation(summary = "리뷰 생성 - 마이페이지용")
    public ReviewIdResponse createReview(
            @PathVariable Long applicationId,
            @RequestBody ReviewCreateDTO reviewCreateDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return reviewService.createReview(applicationId, reviewCreateDTO, principalDetails);
    }

    @GetMapping("/{reviewId}")
    @Operation(summary = "리뷰 1건 상세 보기")
    public ReviewDetailVo getReviewDetail(@PathVariable Long reviewId) {
        return reviewService.getReviewDetail(reviewId);
    }

    @GetMapping("/admin/{programId}")
    @Operation(summary = "어드민 프로그램 별 리뷰 목록")
    public ReviewListResponse getReviewListOfProgram(@PathVariable Long programId, @PageableDefault(size = 20) Pageable pageable) {
        return reviewService.getReviewListOfProgram(programId, pageable);
    }

    @PatchMapping("/{reviewId}")
    @Operation(summary = "어드민 리뷰 수정")
    public ReviewIdResponse updateReviewStatus(@PathVariable Long reviewId, @RequestBody ReviewUpdateDTO reviewUpdateDTO) {
        return reviewService.updateReviewStatus(reviewId, reviewUpdateDTO);
    }
}
