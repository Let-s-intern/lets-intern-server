package com.letsintern.letsintern.domain.memo.helper;

import com.letsintern.letsintern.domain.memo.domain.Memo;
import com.letsintern.letsintern.domain.memo.dto.request.MemoDTO;
import com.letsintern.letsintern.domain.memo.dto.response.MemoListResponse;
import com.letsintern.letsintern.domain.memo.exception.MemoNotFound;
import com.letsintern.letsintern.domain.memo.mapper.MemoMapper;
import com.letsintern.letsintern.domain.memo.repository.MemoRepository;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemoHelper {

    private final MemoMapper memoMapper;
    private final MemoRepository memoRepository;

    public Long createMemo(Long targetUserId, MemoDTO memoDTO, User user) {
        Memo newMemo = memoMapper.toEntity(memoDTO, user.getName(), targetUserId);
        return memoRepository.save(newMemo).getId();
    }

    public Long updateMemo(Long memoId, MemoDTO memoDTO) {
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> {throw MemoNotFound.EXCEPTION;});
        memo.setContents(memoDTO.getContents());
        return memo.getId();
    }

    public MemoListResponse getMemoListOfUser(Long targetUserId) {
        return MemoListResponse.from(memoRepository.findALlByTargetUserId(targetUserId));
    }

    public void deleteMemo(Long memoId) {
        Memo memo = memoRepository.findById(memoId)
                .orElseThrow(() -> {throw MemoNotFound.EXCEPTION;});

        memoRepository.delete(memo);
    }
}
