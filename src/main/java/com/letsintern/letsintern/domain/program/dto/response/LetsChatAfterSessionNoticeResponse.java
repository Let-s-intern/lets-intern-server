package com.letsintern.letsintern.domain.program.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class LetsChatAfterSessionNoticeResponse {

    private List<String> reviewList;

    @Builder
    private LetsChatAfterSessionNoticeResponse(List<String> reviewList) {
        this.reviewList = reviewList;
    }

    public static LetsChatAfterSessionNoticeResponse from(List<String> reviewList) {
        return LetsChatAfterSessionNoticeResponse.builder()
                .reviewList(reviewList)
                .build();
    }

}
