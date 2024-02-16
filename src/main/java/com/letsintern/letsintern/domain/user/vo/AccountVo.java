package com.letsintern.letsintern.domain.user.vo;

import com.letsintern.letsintern.domain.user.domain.AccountType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountVo {

    private AccountType accountType;

    private String accountNumber;

    @Builder
    public AccountVo(AccountType accountType, String accountNumber) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }
}
