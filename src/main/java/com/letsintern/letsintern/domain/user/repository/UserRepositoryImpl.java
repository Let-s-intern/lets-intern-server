package com.letsintern.letsintern.domain.user.repository;

import com.letsintern.letsintern.domain.application.domain.QApplication;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.user.domain.QUser;
import com.letsintern.letsintern.domain.user.domain.UserRole;
import com.letsintern.letsintern.domain.user.filter.UserFilter;
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
    public Page<AdminUserVo> findAllAdminUserVoFiltered(ProgramType programType, Integer programTh, String name, String email, String phoneNum, Pageable pageable) {
        QUser qUser = QUser.user;
        QApplication qApplication = QApplication.application;
        UserFilter userFilter = UserFilter.of(qUser, qApplication, programType, programTh, name, email, phoneNum);

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
                .where(userFilter.eqProgram(), userFilter.eqName(), userFilter.eqEmail(), userFilter.eqPhoneNum())
                .orderBy(qUser.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        count = jpaQueryFactory.select(qUser.count())
                .from(qUser)
                .where(userFilter.eqProgram(), userFilter.eqName(), userFilter.eqEmail(), userFilter.eqPhoneNum());

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
