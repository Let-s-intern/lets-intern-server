package com.letsintern.letsintern.domain.memo.mapper;

import com.letsintern.letsintern.domain.memo.domain.Memo;
import com.letsintern.letsintern.domain.memo.dto.request.MemoDTO;
import com.letsintern.letsintern.domain.memo.dto.response.MemoIdResponse;
import com.letsintern.letsintern.domain.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class MemoMapper {

    public Memo toEntity(MemoDTO memoDTO, String creator, Long targetUserId) {
        return Memo.of(memoDTO, creator, targetUserId);
    }

    public MemoIdResponse toMemoIdResponse(Long memoId) {
        return MemoIdResponse.from(memoId);
    }
}
