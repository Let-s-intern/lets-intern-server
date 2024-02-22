package com.letsintern.letsintern.domain.application.vo;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.application.domain.ApplicationType;
import com.letsintern.letsintern.domain.application.domain.InflowPath;
import com.letsintern.letsintern.domain.user.domain.AccountType;
import com.letsintern.letsintern.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApplicationChallengeAdminVo {

    private Long applicationId;

    private String name;

    private ApplicationType type;

    private String university;

    private String major;

    private Integer grade;

    private String email;

    private String phoneNum;

    private InflowPath inflowPath;

    private String accountType;

    private String accountNumber;

    @Builder
    public ApplicationChallengeAdminVo(Long applicationId, String name, ApplicationType type, String university, String major, Integer grade,
                                       String email, String phoneNum, InflowPath inflowPath, AccountType accountType, String accountNumber) {
        this.applicationId = applicationId;
        this.name = name;
        this.type = type;
        this.university = university;
        this.major = major;
        this.grade = grade;
        this.email = email;
        this.phoneNum = phoneNum;
        this.inflowPath = inflowPath;
        if(accountType != null) this.accountType = accountType.getValue();
        if(accountNumber != null) this.accountNumber = accountNumber;
    }

}
