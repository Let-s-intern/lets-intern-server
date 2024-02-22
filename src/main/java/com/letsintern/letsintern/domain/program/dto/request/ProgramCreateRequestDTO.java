package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProgramCreateRequestDTO {

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

    private Boolean isRefundProgram;

    private List<Long> faqIdList;

}
