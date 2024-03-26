package com.letsintern.letsintern.domain.program.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class LetsChatAfterSessionNoticeResponse {

    private String title;

    private List<String> reviewList;

    @Builder
    private LetsChatAfterSessionNoticeResponse(String title, List<String> reviewList) {
        this.title = title;
        this.reviewList = reviewList;
    }

    public static LetsChatAfterSessionNoticeResponse of(String title, List<String> reviewList) {
        return LetsChatAfterSessionNoticeResponse.builder()
                .title(title)
                .reviewList(reviewList)
                .build();
    }

}
