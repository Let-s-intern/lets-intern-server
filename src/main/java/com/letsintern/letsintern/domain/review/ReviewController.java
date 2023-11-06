package com.letsintern.letsintern.domain.review;

import com.letsintern.letsintern.domain.review.dto.request.ReviewCreateDTO;
import com.letsintern.letsintern.domain.review.dto.response.ReviewIdResponseDTO;
import com.letsintern.letsintern.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Tag(name = "Review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create/{applicationId}")
    @Operation(tags = "리뷰 생성")
    public ReviewIdResponseDTO createReview(@PathVariable Long applicationId, @RequestBody ReviewCreateDTO reviewCreateDTO) {
        return reviewService.createReview(applicationId, reviewCreateDTO);
    }
}
