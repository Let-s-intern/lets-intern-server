package com.letsintern.letsintern.domain.program.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProgramDetailVo {

    private String title;
    private String contents;
    private String notice;

    @JsonIgnore
    private String faqListStr;

    @Builder
    public ProgramDetailVo(String title, String contents, String notice, String faqListStr) {
        this.title = title;
        this.contents = contents;
        this.notice = notice;
        this.faqListStr = faqListStr;
    }
}
