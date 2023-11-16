package com.letsintern.letsintern.domain.program.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Program(ProgramType type, Integer th, String title, Date dueDate, Date announcementDate, Date startDate,
                    String contents, ProgramWay way, String location, String link) {
        this.type = type;
        this.th = th;
        this.title = title;
        this.dueDate = dueDate;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        this.announcementDate = simpleDateFormat.format(announcementDate);
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy년 MM월 dd일 HH:MM");
        this.startDate = simpleDateFormatWithTime.format(startDate);
        this.contents = contents;
        this.way = way;
        this.location = location;
        this.link = link;
    }

    public static Program of(ProgramCreateRequestDTO programCreateRequestDTO) {
        return Program.builder()
                .type(programCreateRequestDTO.getType())
                .th(programCreateRequestDTO.getTh())
                .title(programCreateRequestDTO.getTitle())
                .dueDate(programCreateRequestDTO.getDueDate())
                .announcementDate(programCreateRequestDTO.getAnnouncementDate())
                .startDate(programCreateRequestDTO.getStartDate())
                .contents(programCreateRequestDTO.getContents())
                .way(programCreateRequestDTO.getWay())
                .location(programCreateRequestDTO.getLocation())
                .link(programCreateRequestDTO.getLink())
                .build();
    }
}
