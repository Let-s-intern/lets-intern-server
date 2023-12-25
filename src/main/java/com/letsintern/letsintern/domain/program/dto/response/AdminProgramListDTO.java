package com.letsintern.letsintern.domain.program.dto.response;

import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.global.common.dto.PageInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class AdminProgramListDTO {

    private List<Program> programList;
    private PageInfo pageInfo;

    @Builder
    private AdminProgramListDTO(Page<Program> programList) {
        if(programList.hasContent()) this.programList = programList.getContent();
        this.pageInfo = PageInfo.of(programList);
    }

    public static AdminProgramListDTO from(Page<Program> programList) {
        return AdminProgramListDTO.builder()
                .programList(programList)
                .build();
    }
}
