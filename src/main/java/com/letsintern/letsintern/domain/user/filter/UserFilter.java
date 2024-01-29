package com.letsintern.letsintern.domain.user.filter;

import com.letsintern.letsintern.domain.application.domain.QApplication;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.user.domain.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Builder;

public class UserFilter {

    private QUser qUser;
    private QApplication qApplication;

    private ProgramType programType;
    private Integer programTh;
    private String name;
    private String email;
    private String phoneNum;

    @Builder
    private UserFilter(QUser qUser, QApplication qApplication, ProgramType programType, Integer programTh, String name, String email, String phoneNum) {
        this.qUser = qUser;
        this.qApplication = qApplication;
        this.programType = programType;
        this.programTh = programTh;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public static UserFilter of(QUser qUser, QApplication qApplication, ProgramType programType, Integer programTh, String name, String email, String phoneNum) {
        return UserFilter.builder()
                .qUser(qUser)
                .qApplication(qApplication)
                .programType(programType)
                .programTh(programTh)
                .name(name)
                .email(email)
                .phoneNum(phoneNum)
                .build();
    }

    public BooleanBuilder eqProgram() {
        if(programType == null)
            return null;

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(programTh == null) {
            booleanBuilder.and(qApplication.program.type.eq(programType))
                    .and(qApplication.program.th.eq(programTh));
        } else {
            booleanBuilder.and(qApplication.program.type.eq(programType));
        }

        return booleanBuilder;
    }

    public BooleanExpression eqName() {
        return name == null ? null : qUser.name.containsIgnoreCase(name);
    }

    public BooleanExpression eqEmail() {
        return email == null ? null : qUser.email.containsIgnoreCase(email);
    }

    public BooleanExpression eqPhoneNum() {
        return phoneNum == null ? null : qUser.phoneNum.containsIgnoreCase(phoneNum);
    }
}
