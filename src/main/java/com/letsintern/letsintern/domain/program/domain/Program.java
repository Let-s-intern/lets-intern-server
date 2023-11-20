package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.domain.faq.domain.Faq;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Integer maxHeadcount = -1;

    @NotNull
    private Integer headcount = 0;

    @NotNull
    private Date dueDate;

    @NotNull
    private String announcementDate;

    @NotNull
    private String startDate;

    @NotNull
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
    @Column(length = 32)
    @Enumerated(EnumType.STRING)
    private ProgramStatus status = ProgramStatus.OPEN;

    @NotNull
    private Boolean isApproved = false;

    @NotNull
    private Boolean isVisible = false;

    @OneToMany(mappedBy = "program", orphanRemoval = true)
    List<Faq> faqList = new ArrayList<>();


    @Builder
    private Program(ProgramType type, Integer th, String title, Integer maxHeadcount,
                    Date dueDate, Date announcementDate, Date startDate,
                    String contents, ProgramWay way, String location, String link, String notice) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:MM");
        this.type = type;
        this.th = th;
        this.title = title;
        this.maxHeadcount = maxHeadcount;
        this.dueDate = dueDate;
        this.announcementDate = simpleDateFormat.format(announcementDate);
        this.startDate = simpleDateFormat.format(startDate);
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
                .maxHeadcount(programCreateRequestDTO.getMaxHeadcount())
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
