package com.letsintern.letsintern.domain.application.filter;

import com.letsintern.letsintern.domain.application.domain.QApplication;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Builder;

public class ApplicationFilter {

    private QApplication qApplication;
    private String name;
    private String email;
    private String phoneNum;

    @Builder
    private ApplicationFilter(QApplication qApplication, String name, String email, String phoneNum) {
        this.qApplication = qApplication;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public static ApplicationFilter of(QApplication qApplication, String name, String email, String phoneNum) {
        return ApplicationFilter.builder()
                .qApplication(qApplication)
                .name(name)
                .email(email)
                .phoneNum(phoneNum)
                .build();
    }

    public BooleanExpression eqName() {
        return name == null ? null : qApplication.user.name.containsIgnoreCase(name);
    }

    public BooleanExpression eqEmail() {
        return email == null ? null : qApplication.user.email.containsIgnoreCase(email);
    }

    public BooleanExpression eqPhoneNum() {
        return phoneNum == null ? null : qApplication.user.phoneNum.containsIgnoreCase(phoneNum);
    }
}
