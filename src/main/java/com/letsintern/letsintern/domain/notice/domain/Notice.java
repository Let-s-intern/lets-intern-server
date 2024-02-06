package com.letsintern.letsintern.domain.notice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.notice.dto.request.NoticeCreateDTO;
import com.letsintern.letsintern.domain.program.domain.Program;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NoticeType type;

    @NotNull
    private String title;

    @NotNull
    private String link;

    @NotNull
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    @JsonIgnore
    private Program program;

    @Builder
    private Notice(Program program, NoticeType type, String title, String link) {
        this.program = program;
        this.type = type;
        this.title = title;
        this.link = link;
        this.createdAt = LocalDate.now();
    }

    public static Notice of(Program program, NoticeCreateDTO noticeCreateDTO) {
        return Notice.builder()
                .program(program)
                .type(noticeCreateDTO.getType())
                .title(noticeCreateDTO.getTitle())
                .link(noticeCreateDTO.getLink())
                .build();
    }

}
