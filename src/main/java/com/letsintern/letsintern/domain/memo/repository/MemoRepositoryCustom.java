package com.letsintern.letsintern.domain.memo.repository;

import com.letsintern.letsintern.domain.memo.domain.Memo;

import java.util.List;

public interface MemoRepositoryCustom {

    List<Memo> findALlByTargetUserId(Long targetUserId);

}
