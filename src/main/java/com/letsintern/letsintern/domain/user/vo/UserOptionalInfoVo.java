package com.letsintern.letsintern.domain.user.vo;

import com.letsintern.letsintern.domain.user.domain.AccountType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserOptionalInfoVo {

    private Long userId;
    private String university;
    private String major;

    private AccountType accountType;
    private String accountNumber;

    @Builder
    private UserOptionalInfoVo(Long userId, String university, String major, AccountType accountType, String accountNumber) {
        this.userId = userId;
        this.university = university;
        this.major = major;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }

    public static UserOptionalInfoVo of(Long userId, String university, String major, AccountType accountType, String accountNumber) {
        return UserOptionalInfoVo.builder()
                .userId(userId)
                .university(university)
                .major(major)
                .accountType(accountType)
                .accountNumber(accountNumber)
                .build();
    }
}
