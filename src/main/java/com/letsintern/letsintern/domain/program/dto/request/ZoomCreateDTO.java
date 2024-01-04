package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ZoomCreateDTO {

    private ProgramType type;

    private Integer th;

    private String title;

    private LocalDateTime startDate;

}
