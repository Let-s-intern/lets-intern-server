package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import lombok.Getter;

import java.util.Date;

@Getter
public class ProgramUpdateRequestDTO {

    private ProgramType type;

    private Integer th;

    private Date dueDate;

    private Date announcementDate;

    private Date startDate;

    private ProgramStatus status;
}
