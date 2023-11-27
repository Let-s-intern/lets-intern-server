package com.letsintern.letsintern.domain.program.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
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
    private ProgramType type;

    @JsonIgnore
    private String faqListStr;

    @Builder
    public ProgramDetailVo(String title, String contents, String notice, ProgramType type, String faqListStr) {
        this.title = title;
        this.contents = contents;
        this.notice = notice;
        this.type = type;
        this.faqListStr = faqListStr;
    }
}
