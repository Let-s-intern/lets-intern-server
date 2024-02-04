package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramStatus;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ProgramUpdateRequestDTO {

    private ProgramType type;

    private Integer th;

    private String title;

    private Integer headcount;

    private LocalDateTime dueDate;

    private LocalDateTime announcementDate;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String contents;

    private ProgramWay way;

    private String location;

    private String notice;

    private ProgramStatus status;

    private Boolean isVisible;

    private List<Long> faqIdList;
}
