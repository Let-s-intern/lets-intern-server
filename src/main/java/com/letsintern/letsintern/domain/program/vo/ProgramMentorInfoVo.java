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
    private ProgramMentorInfoVo(ProgramWay way, String location, String link, String linkPassword,
                               LocalDateTime startDate, LocalDateTime endDate, Integer applicationCount) {
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
