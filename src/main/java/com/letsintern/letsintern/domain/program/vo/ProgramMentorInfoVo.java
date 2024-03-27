package com.letsintern.letsintern.domain.program.vo;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProgramMentorInfoVo {

    @NotNull
    private String title;

    @NotNull
    private ProgramWay way;

    private String location;

    private String link;

    private String linkPassword;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private Integer applicationCount;

    @Builder
    private ProgramMentorInfoVo(String title, ProgramWay way, String location, String link, String linkPassword,
                               LocalDateTime startDate, LocalDateTime endDate, Integer applicationCount) {
        this.title = title;
        this.way = way;
        this.location = location;
        this.link = link;
        this.linkPassword = linkPassword;
        this.startDate = startDate;
        this.endDate = endDate;
        this.applicationCount = applicationCount;
    }

    public static ProgramMentorInfoVo from(Program program) {
        return ProgramMentorInfoVo.builder()
                .title(program.getTitle())
                .way(program.getWay())
                .location(program.getLocation())
                .link(program.getLink())
                .linkPassword(program.getLinkPassword())
                .startDate(program.getStartDate())
                .endDate(program.getEndDate())
                .applicationCount(program.getApplicationCount())
                .build();
    }

}
