package com.letsintern.letsintern.domain.program.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProgramDetailVo {

    private String title;
    private String contents;

    //private List<Faq> faqList;

    @Builder
    public ProgramDetailVo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
