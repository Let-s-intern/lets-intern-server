package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.program.domain.Program;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProgramTotalListDTO {

    List<Program> programList;

    @Builder
    private ProgramTotalListDTO(List<Program> programList) {
        this.programList = programList;
    }

    public static ProgramTotalListDTO from(List<Program> programList) {
        return ProgramTotalListDTO.builder()
                .programList(programList)
                .build();
    }
}
