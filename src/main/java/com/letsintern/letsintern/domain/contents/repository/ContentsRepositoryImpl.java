package com.letsintern.letsintern.domain.contents.repository;

import com.letsintern.letsintern.domain.contents.domain.Contents;
import com.letsintern.letsintern.domain.contents.domain.ContentsTopic;
import com.letsintern.letsintern.domain.contents.domain.ContentsType;
import com.letsintern.letsintern.domain.contents.domain.QContents;
import com.letsintern.letsintern.domain.contents.vo.ContentsAdminVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContentsRepositoryImpl implements ContentsRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Optional<Contents> findOneByTypeAndTopicOrderByIdDesc(ContentsType type, ContentsTopic topic) {
        QContents qContents = QContents.contents;
        return Optional.ofNullable(jpaQueryFactory
                .select(qContents)
                .from(qContents)
                .where(qContents.type.eq(type),
                        qContents.topic.eq(topic))
                .orderBy(qContents.id.desc())
                .fetchFirst());
    }

    @Override
    public Page<ContentsAdminVo> getContentsAdminVoList(ContentsTopic contentsTopic, Pageable pageable) {
        QContents qContents = QContents.contents;
        List<ContentsAdminVo> contentsAdminVos;
        JPAQuery<Long> count;

        if(contentsTopic != null) {
            contentsAdminVos = jpaQueryFactory
                    .select(Projections.constructor(ContentsAdminVo.class,
                            qContents.id,
                            qContents.type,
                            qContents.title,
                            qContents.createdAt,
                            qContents.topic,
                            qContents.link))
                    .from(qContents)
                    .where(qContents.topic.eq(contentsTopic))
                    .orderBy(qContents.id.asc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = jpaQueryFactory.select(qContents.count())
                    .from(qContents)
                    .where(qContents.topic.eq(contentsTopic));
        }

        else {
            contentsAdminVos = jpaQueryFactory
                    .select(Projections.constructor(ContentsAdminVo.class,
                            qContents.id,
                            qContents.type,
                            qContents.title,
                            qContents.createdAt,
                            qContents.topic,
                            qContents.link))
                    .from(qContents)
                    .orderBy(qContents.id.asc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            count = jpaQueryFactory.select(qContents.count())
                    .from(qContents);
        }

        return PageableExecutionUtils.getPage(contentsAdminVos, pageable, count::fetchOne);
    }

    @Override
    public Optional<ContentsAdminVo> findContentsAdminVo(Long contentsId) {
        QContents qContents = QContents.contents;

        return Optional.ofNullable(jpaQueryFactory
                .select(Projections.constructor(ContentsAdminVo.class,
                        qContents.id,
                        qContents.type,
                        qContents.title,
                        qContents.createdAt,
                        qContents.topic,
                        qContents.link))
                .from(qContents)
                .where(qContents.id.eq(contentsId))
                .fetchFirst());
    }
}
