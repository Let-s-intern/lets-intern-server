package com.letsintern.letsintern.domain.memo.dto.response;

import com.letsintern.letsintern.domain.memo.domain.Memo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MemoListResponse {

    List<Memo> memoList;

    @Builder
    private MemoListResponse(List<Memo> memoList) {
        this.memoList = memoList;
    }

    public static MemoListResponse from(List<Memo> memoList) {
        return MemoListResponse.builder()
                .memoList(memoList)
                .build();
    }
}
