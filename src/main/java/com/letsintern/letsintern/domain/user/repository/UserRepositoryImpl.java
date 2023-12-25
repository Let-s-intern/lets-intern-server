package com.letsintern.letsintern.domain.user.repository;

import com.letsintern.letsintern.domain.user.domain.QUser;
import com.letsintern.letsintern.domain.user.domain.UserRole;
import com.letsintern.letsintern.domain.user.vo.AdminMangerVo;
import com.letsintern.letsintern.domain.user.vo.AdminUserVo;
import com.querydsl.core.types.Projections;
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
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<AdminUserVo> findAllAdminUserVo(Pageable pageable) {
        QUser qUser = QUser.user;
        List<AdminUserVo> adminUserVos;
        JPAQuery<Long> count;

        adminUserVos = jpaQueryFactory
                .select(Projections.constructor(AdminUserVo.class,
                        qUser.id,
                        qUser.name,
                        qUser.email,
                        qUser.phoneNum,
                        qUser.university,
                        qUser.major,
                        qUser.signedUpAt,
                        qUser.managerId))
                .from(qUser)
                .orderBy(qUser.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qUser.count())
                .from(qUser);

        return PageableExecutionUtils.getPage(adminUserVos, pageable, count::fetchOne);
    }

    @Override
    public List<AdminMangerVo> findManagerList() {
        QUser qUser = QUser.user;

        return jpaQueryFactory
                .select(Projections.constructor(AdminMangerVo.class,
                        qUser.id,
                        qUser.name
                ))
                .from(qUser)
                .where(qUser.role.eq(UserRole.ROLE_ADMIN))
                .fetch();
    }
}
