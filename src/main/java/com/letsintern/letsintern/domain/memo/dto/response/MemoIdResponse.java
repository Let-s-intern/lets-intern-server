package com.letsintern.letsintern.domain.memo.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemoIdResponse {

    Long memoId;

    @Builder
    private MemoIdResponse(Long memoId) {
        this.memoId = memoId;
    }

    public static MemoIdResponse from(Long memoId) {
        return MemoIdResponse.builder()
                .memoId(memoId)
                .build();
    }
}
