package com.letsintern.letsintern.domain.program.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProgramIdResponseDTO {

    private Long programId;

    @Builder
    private ProgramIdResponseDTO(Long programId) {
        this.programId = programId;
    }

    public static ProgramIdResponseDTO from(Long programId) {
        return ProgramIdResponseDTO.builder()
                .programId(programId)
                .build();
    }
}
