package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProgramListDTO {

    private List<ProgramThumbnailVo> programList;

    @Builder
    private ProgramListDTO(List<ProgramThumbnailVo> programList) {
        this.programList = programList;
    }

    public static ProgramListDTO from(List<ProgramThumbnailVo> programList) {
        return ProgramListDTO.builder()
                .programList(programList)
                .build();
    }
}
