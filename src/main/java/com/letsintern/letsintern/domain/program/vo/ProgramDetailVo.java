package com.letsintern.letsintern.domain.program.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProgramDetailVo {

    private String userName;
    private String userEmail;
    private String userPhoneNum;

    private String title;
    private String contents;

    @Builder
    public ProgramDetailVo(String userName, String userEmail, String userPhoneNum,
                           String title, String contents) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoneNum = userPhoneNum;
        this.title = title;
        this.contents = contents;
    }
}
