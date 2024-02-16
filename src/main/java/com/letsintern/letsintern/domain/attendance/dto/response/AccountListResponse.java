package com.letsintern.letsintern.domain.attendance.dto.response;

import com.letsintern.letsintern.domain.user.vo.AccountVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AccountListResponse {

    private List<AccountVo> accountVoList;

    @Builder
    private AccountListResponse(List<AccountVo> accountVoList) {
        this.accountVoList = accountVoList;
    }

    public static AccountListResponse from(List<AccountVo> accountVoList) {
        return AccountListResponse.builder()
                .accountVoList(accountVoList)
                .build();
    }
}
