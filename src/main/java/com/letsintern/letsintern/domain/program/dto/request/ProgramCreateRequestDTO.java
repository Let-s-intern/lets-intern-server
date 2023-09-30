package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProgramCreateRequestDTO {

    private ProgramType type;

}
