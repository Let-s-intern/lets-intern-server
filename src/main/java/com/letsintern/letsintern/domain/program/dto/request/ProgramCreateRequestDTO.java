package com.letsintern.letsintern.domain.program.dto.request;

import com.letsintern.letsintern.domain.faq.dto.FaqDTO;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.program.domain.ProgramWay;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProgramCreateRequestDTO {

    private ProgramType type;

    private Integer th;

    private String title;

    private Integer headcount;

    private Date dueDate;

    private Date announcementDate;

    private Date startDate;

    private Date doneDate;

    private String contents;

    private ProgramWay way;

    private String location;

    private String link;

    private String notice;

    private List<FaqDTO> faqDTOList;

}
