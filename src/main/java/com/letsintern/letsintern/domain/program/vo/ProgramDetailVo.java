package com.letsintern.letsintern.domain.program.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProgramDetailVo {

    private ProgramStatus status;
    private String title;
    private String contents;
    private String notice;
    private ProgramType type;
    private ProgramWay way;
    private String location;

    @JsonIgnore
    private String faqListStr;

    @Builder
    public ProgramDetailVo(ProgramStatus status, String title, String contents, String notice,
                           ProgramType type, ProgramWay way, String location, String faqListStr) {
        this.status = status;
        this.title = title;
        this.contents = contents;
        this.notice = notice;
        this.type = type;
        this.way = way;
        this.location = location;
        this.faqListStr = faqListStr;
    }
}
