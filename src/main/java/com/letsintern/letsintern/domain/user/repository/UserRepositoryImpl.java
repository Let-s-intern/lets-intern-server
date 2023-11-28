package com.letsintern.letsintern.domain.user.repository;

import com.letsintern.letsintern.domain.user.domain.QUser;
import com.letsintern.letsintern.domain.user.domain.UserRole;
import com.letsintern.letsintern.domain.user.vo.AdminMangerVo;
import com.letsintern.letsintern.domain.user.vo.AdminUserVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<AdminUserVo> findAllAdminUserVo(Pageable pageable) {
        QUser qUser = QUser.user;

        return jpaQueryFactory
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
    }

    @Override
    public List<AdminUserVo> findAdminUserVoByName(String name) {
        QUser qUser = QUser.user;

        return jpaQueryFactory
                .select(Projections.constructor(AdminUserVo.class,
                        qUser.id,
                        qUser.name,
                        qUser.email,
                        qUser.phoneNum,
                        qUser.university,
                        qUser.major,
                        qUser.signedUpAt))
                .from(qUser)
                .where(qUser.name.eq(name))
                .fetch();
    }

    @Override
    public List<AdminUserVo> findAdminUserVoByEmail(String email) {
        QUser qUser = QUser.user;

        return jpaQueryFactory
                .select(Projections.constructor(AdminUserVo.class,
                        qUser.id,
                        qUser.name,
                        qUser.email,
                        qUser.phoneNum,
                        qUser.university,
                        qUser.major,
                        qUser.signedUpAt))
                .from(qUser)
                .where(qUser.email.eq(email))
                .fetch();
    }

    @Override
    public List<AdminUserVo> findAdminUserVoByPhoneNum(String phoneNum) {
        QUser qUser = QUser.user;

        return jpaQueryFactory
                .select(Projections.constructor(AdminUserVo.class,
                        qUser.id,
                        qUser.name,
                        qUser.email,
                        qUser.phoneNum,
                        qUser.university,
                        qUser.major,
                        qUser.signedUpAt))
                .from(qUser)
                .where(qUser.phoneNum.eq(phoneNum))
                .fetch();
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
