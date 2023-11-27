package com.letsintern.letsintern.domain.memo.repository;

import com.letsintern.letsintern.domain.memo.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long>, MemoRepositoryCustom {
}
