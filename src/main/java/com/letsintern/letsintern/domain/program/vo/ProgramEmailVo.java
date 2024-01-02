package com.letsintern.letsintern.domain.program.vo;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProgramEmailVo {

    private ProgramType type;
    private Integer th;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private ProgramWay way;
    private String location;

    @Builder
    private ProgramEmailVo(ProgramType type, Integer th, String title, LocalDateTime startDate, LocalDateTime endDate, ProgramWay way, String location) {
        this.type = type;
        this.th = th;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.way = way;
        this.location = location;
    }

    public static ProgramEmailVo from(Program program) {
        return ProgramEmailVo.builder()
                .type(program.getType())
                .th(program.getTh())
                .title(program.getTitle())
                .startDate(program.getStartDate())
                .endDate(program.getEndDate())
                .way(program.getWay())
                .location(program.getLocation())
                .build();
    }
}
