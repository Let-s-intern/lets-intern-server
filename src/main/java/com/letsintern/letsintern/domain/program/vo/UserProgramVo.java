package com.letsintern.letsintern.domain.program.vo;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserProgramVo {

    private Long id;
    private ProgramType type;
    private Integer th;
    private String title;

    @Builder
    public UserProgramVo(Long id, ProgramType type, Integer th, String title) {
        this.id = id;
        this.type = type;
        this.th = th;
        this.title = title;
    }

    public static UserProgramVo from(Program program) {
        return UserProgramVo.builder()
                .id(program.getId())
                .type(program.getType())
                .th(program.getTh())
                .title(program.getTitle())
                .build();
    }
}
