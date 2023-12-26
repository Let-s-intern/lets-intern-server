package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.program.vo.ProgramThumbnailVo;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProgramListDTO {

    private List<ProgramThumbnailVo> programList = new ArrayList<>();
    private PageInfo pageInfo;

    @Builder
    private ProgramListDTO(Page<ProgramThumbnailVo> programList) {
        if(programList.hasContent()) this.programList = programList.getContent();
        this.pageInfo = PageInfo.of(programList);
    }

    public static ProgramListDTO from(Page<ProgramThumbnailVo> programList) {
        return ProgramListDTO.builder()
                .programList(programList)
                .build();
    }
}
