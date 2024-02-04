package com.letsintern.letsintern.domain.mission.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.global.common.util.StringUtils;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission {

    @Id
    @Column(name = "mission_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer attendanceCount;

    @NotNull
    private Integer refund;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MissionType type;

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

    @Nullable
    private String contentsListStr;

    @NotNull
    private Boolean isVisible;

    @NotNull
    private Boolean isRefunded;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    @JsonIgnore
    private Program program;

    @Builder
    private Mission(Program program, Integer refund, MissionType type, String title, String contents, Integer th, List<Long> contentsIdList) {
        this.program = program;
        this.attendanceCount = 0;
        this.refund = refund;
        this.type = type;
        this.title = title;
        this.contents = contents;

        this.th = th;
        this.startDate = program.getStartDate().plusDays(th-1);
        this.endDate = program.getStartDate().plusDays(th);

        this.contentsListStr = StringUtils.listToString(contentsIdList);
        this.isVisible = false;
        this.isRefunded = false;
    }

    public static Mission of(Program program, MissionCreateDTO missionCreateDTO) {
        return Mission.builder()
                .program(program)
                .refund(missionCreateDTO.getRefund())
                .type(missionCreateDTO.getType())
                .title(missionCreateDTO.getTitle())
                .contents(missionCreateDTO.getContents())
                .th(missionCreateDTO.getTh())
                .contentsIdList(missionCreateDTO.getContentsIdList())
                .build();
    }
}
