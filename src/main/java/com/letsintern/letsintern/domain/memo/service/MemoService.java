package com.letsintern.letsintern.domain.memo.service;

import com.letsintern.letsintern.domain.memo.dto.request.MemoDTO;
import com.letsintern.letsintern.domain.memo.dto.response.MemoIdResponse;
import com.letsintern.letsintern.domain.memo.dto.response.MemoListResponse;
import com.letsintern.letsintern.domain.memo.helper.MemoHelper;
import com.letsintern.letsintern.domain.memo.mapper.MemoMapper;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.exception.UserNotFound;
import com.letsintern.letsintern.domain.user.repository.UserRepository;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final UserRepository userRepository;
    private final MemoHelper memoHelper;
    private final MemoMapper memoMapper;

    public MemoIdResponse createMemo(Long targetUserId, MemoDTO memoDTO, PrincipalDetails principalDetails) {
        final User user = principalDetails.getUser();
        return memoMapper.toMemoIdResponse(memoHelper.createMemo(targetUserId, memoDTO, user));
    }

    @Transactional
    public MemoIdResponse updateMemo(Long memoId, MemoDTO memoDTO) {
        return memoMapper.toMemoIdResponse(memoHelper.updateMemo(memoId, memoDTO));
    }

    public MemoListResponse getMemoListOfUser(Long targetUserId, Pageable pageable) {
        return memoHelper.getMemoListOfUser(targetUserId, pageable);
    }

    @Transactional
    public void deleteMemo(Long memoId) {
        memoHelper.deleteMemo(memoId);
    }
}
