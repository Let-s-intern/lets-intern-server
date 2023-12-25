package com.letsintern.letsintern.domain.memo.repository;

import com.letsintern.letsintern.domain.memo.domain.Memo;
import com.letsintern.letsintern.domain.memo.domain.QMemo;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemoRepositoryImpl implements MemoRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Memo> findALlByTargetUserId(Long targetUserId, Pageable pageable) {
        QMemo qMemo = QMemo.memo;
        List<Memo> memoList;
        JPAQuery<Long> count;

        memoList = jpaQueryFactory
                .selectFrom(qMemo)
                .where(qMemo.targetUserId.eq(targetUserId))
                .orderBy(qMemo.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qMemo.count())
                .where(qMemo.targetUserId.eq(targetUserId))
                .from(qMemo);

        return PageableExecutionUtils.getPage(memoList, pageable, count::fetchOne);
    }
}
