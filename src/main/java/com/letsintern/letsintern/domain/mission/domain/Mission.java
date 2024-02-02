package com.letsintern.letsintern.domain.mission.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.program.domain.Program;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer attendanceCount = 0;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MissionStatus status = MissionStatus.CREATED;

    @NotNull
    private String title;

    @NotNull
    private String contents;

    @NotNull
    private Integer th;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private Boolean isRefunded = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    @JsonIgnore
    private Program program;

    @Builder
    private Mission(Program program, String title, String contents, Integer th, LocalDateTime startDate, LocalDateTime endDate) {
        this.program = program;
        this.title = title;
        this.contents = contents;
        this.th = th;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Mission of(Program program, String title, String contents, Integer th, LocalDateTime startDate, LocalDateTime endDate) {
        return Mission.builder()
                .program(program)
                .title(title)
                .contents(contents)
                .th(th)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
