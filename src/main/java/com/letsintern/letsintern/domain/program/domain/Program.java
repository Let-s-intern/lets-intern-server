package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Program {

    @Id
    @Column(name = "program_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private ProgramType type;

    @NotNull
    private Integer th;

    @NotNull
    @Column(length = 50)
    private String title;

    @NotNull
    private Integer headcount = 0;

    @NotNull
    private Date dueDate;

    @NotNull
    private Date announcementDate;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    @Column(length = 300)
    private String contents;

    @NotNull
    private ProgramWay way;

    @Nullable
    private String location;

    @Nullable
    private String link;

    @NotNull
    private String notice;

    @NotNull
    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private ProgramStatus status = ProgramStatus.OPEN;

    @NotNull
    private Boolean isVisible = false;


    @Builder
    private Program(ProgramType type, Integer th, String title, Integer headcount,
                    Date dueDate, Date announcementDate, Date startDate, Date endDate,
                    String contents, ProgramWay way, String location, String link, String notice) {
        this.type = type;
        this.th = th;
        this.title = title;
        this.headcount = headcount;
        this.dueDate = dueDate;
        this.announcementDate = announcementDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contents = contents;
        this.way = way;
        this.location = location;
        this.link = link;
        this.notice = notice;
    }

    public static Program of(ProgramCreateRequestDTO programCreateRequestDTO) {
        return Program.builder()
                .type(programCreateRequestDTO.getType())
                .th(programCreateRequestDTO.getTh())
                .title(programCreateRequestDTO.getTitle())
                .headcount(programCreateRequestDTO.getHeadcount())
                .dueDate(programCreateRequestDTO.getDueDate())
                .announcementDate(programCreateRequestDTO.getAnnouncementDate())
                .startDate(programCreateRequestDTO.getStartDate())
                .contents(programCreateRequestDTO.getContents())
                .way(programCreateRequestDTO.getWay())
                .location(programCreateRequestDTO.getLocation())
                .link(programCreateRequestDTO.getLink())
                .notice(programCreateRequestDTO.getNotice())
                .build();
    }
}
