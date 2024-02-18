package com.letsintern.letsintern.domain.user.vo;

import com.letsintern.letsintern.domain.user.domain.AccountType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountVo {

    private String name;
    private AccountType accountType;

    private String accountNumber;

    @Builder
    public AccountVo(String name, AccountType accountType, String accountNumber) {
        this.name = name;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }
}
