package com.letsintern.letsintern.domain.memo.dto.response;

import com.letsintern.letsintern.domain.memo.domain.Memo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemoListResponse {

    private List<Memo> memoList = new ArrayList<>();
    private PageInfo pageInfo;

    @Builder
    private MemoListResponse(Page<Memo> memoList) {
        if(memoList.hasContent()) this.memoList = memoList.getContent();
        this.pageInfo = PageInfo.of(memoList);
    }

    public static MemoListResponse from(Page<Memo> memoList) {
        return MemoListResponse.builder()
                .memoList(memoList)
                .build();
    }
}
