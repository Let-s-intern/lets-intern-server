package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import lombok.Getter;

import java.util.Date;

@Getter
public class ProgramUpdateRequestDTO {

    private ProgramType type;

    private Integer th;

    private String title;

    private Date dueDate;

    private Date announcementDate;

    private Date startDate;

    private String contents;

    private ProgramWay way;

    private String location;

    private String link;

    private String questions;

    private ProgramStatus status;

    private Boolean isApproved;

    private Boolean isVisible;
}
