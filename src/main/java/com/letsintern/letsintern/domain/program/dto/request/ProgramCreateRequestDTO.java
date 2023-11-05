package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class ProgramCreateRequestDTO {

    private ProgramType type;

    private Date dueDate;

    private Date announcementDate;

    private Date startDate;

}
