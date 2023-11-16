package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.program.domain.Program;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProgramAdminListDTO {

    private List<Program> programList;

    @Builder
    private ProgramAdminListDTO(List<Program> programList) {
        this.programList = programList;
    }

    public static ProgramAdminListDTO from(List<Program> programList) {
        return ProgramAdminListDTO.builder()
                .programList(programList)
                .build();
    }
}
