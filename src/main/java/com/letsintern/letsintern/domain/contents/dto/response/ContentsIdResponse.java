package com.letsintern.letsintern.domain.contents.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ContentsIdResponse {

    private Long contentsId;

    @Builder
    private ContentsIdResponse(Long contentsId) {
        this.contentsId = contentsId;
    }

    public static ContentsIdResponse from(Long contentsId) {
        return ContentsIdResponse.builder()
                .contentsId(contentsId)
                .build();
    }
}
