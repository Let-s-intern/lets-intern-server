package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProgramListDTO {

    private List<ProgramThumbnailVo> openProgramList;
    private List<ProgramThumbnailVo> closedProgramList;

    @Builder
    private ProgramListDTO(List<ProgramThumbnailVo> openProgramList, List<ProgramThumbnailVo> closedProgramList) {
        this.openProgramList = openProgramList;
        this.closedProgramList = closedProgramList;
    }

    public static ProgramListDTO from(List<ProgramThumbnailVo> openProgramList, List<ProgramThumbnailVo> closedProgramList) {
        return ProgramListDTO.builder()
                .openProgramList(openProgramList)
                .closedProgramList(closedProgramList)
                .build();
    }
}
