package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.program.domain.Program;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AdminProgramListDTO {

    private List<Program> programList;

    @Builder
    private AdminProgramListDTO(List<Program> programList) {
        this.programList = programList;
    }

    public static AdminProgramListDTO from(List<Program> programList) {
        return AdminProgramListDTO.builder()
                .programList(programList)
                .build();
    }
}
