package com.letsintern.letsintern.domain.memo.repository;

import com.letsintern.letsintern.domain.memo.domain.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemoRepositoryCustom {

    Page<Memo> findALlByTargetUserId(Long targetUserId, Pageable pageable);

}
