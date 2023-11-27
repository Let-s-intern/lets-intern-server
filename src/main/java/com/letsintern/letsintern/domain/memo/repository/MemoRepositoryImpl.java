package com.letsintern.letsintern.domain.memo.repository;

import com.letsintern.letsintern.domain.memo.domain.Memo;
import com.letsintern.letsintern.domain.memo.domain.QMemo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemoRepositoryImpl implements MemoRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Memo> findALlByTargetUserId(Long targetUserId) {
        QMemo qMemo = QMemo.memo;

        return jpaQueryFactory
                .selectFrom(qMemo)
                .where(qMemo.targetUserId.eq(targetUserId))
                .orderBy(qMemo.id.desc())
                .fetch();
    }
}
