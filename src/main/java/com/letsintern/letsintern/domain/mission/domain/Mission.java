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
    @Enumerated(EnumType.STRING)
    private MissionType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MissionTopic topic;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MissionStatus status = MissionStatus.CREATED;

    @Nullable
    private Integer refund;

    @NotNull
    private Integer attendanceCount;

    @NotNull
    private String title;

    @NotNull
    private String contents;

    @NotNull
    private String guide;

    @NotNull
    private Integer th;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    private String template;

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
    private Mission(Program program, MissionTopic topic, MissionType type, Integer refund, String title, String contents, String guide, Integer th, String template, List<Long> contentsIdList) {
        this.program = program;
        this.type = type;
        this.topic = topic;

        this.refund = refund;
        this.attendanceCount = 0;
        this.title = title;
        this.contents = contents;
        this.guide = guide;

        this.th = th;
        if(th == 1) this.startDate = program.getStartDate();
        else this.startDate = program.getStartDate().plusDays(th - 1).withHour(6);
        this.endDate = this.startDate.withHour(23).withMinute(59).withSecond(59);

        this.template = template;
        this.contentsListStr = StringUtils.listToString(contentsIdList);
        this.isVisible = false;
        this.isRefunded = false;
    }

    public static Mission of(Program program, MissionCreateDTO missionCreateDTO) {
        return Mission.builder()
                .program(program)
                .type(missionCreateDTO.getType())
                .topic(missionCreateDTO.getTopic())
                .refund(missionCreateDTO.getRefund())
                .title(missionCreateDTO.getTitle())
                .contents(missionCreateDTO.getContents())
                .guide(missionCreateDTO.getGuide())
                .th(missionCreateDTO.getTh())
                .template(missionCreateDTO.getTemplate())
                .contentsIdList(missionCreateDTO.getContentsIdList())
                .build();
    }
}
