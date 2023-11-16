package com.letsintern.letsintern.domain.program.vo;

import com.letsintern.letsintern.domain.faq.domain.Faq;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProgramDetailVo {

    private String userName;
    private String userEmail;
    private String userPhoneNum;

    private String title;
    private String contents;

    private List<Faq> faqList;

    @Builder
    public ProgramDetailVo(String userName, String userEmail, String userPhoneNum,
                           String title, String contents, List<Faq> faqList) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoneNum = userPhoneNum;
        this.title = title;
        this.contents = contents;
        this.faqList = faqList;
    }
}
