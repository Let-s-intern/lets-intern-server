package com.letsintern.letsintern.domain.mission.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.attendance.domain.Attendance;
import com.letsintern.letsintern.domain.contents.domain.Contents;
import com.letsintern.letsintern.domain.mission.dto.request.MissionCreateDTO;
import com.letsintern.letsintern.domain.program.domain.Program;
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
    private MissionStatus status = MissionStatus.WAITING;

    @Nullable
    private Integer refund;

    @NotNull
    private Integer attendanceCount;

    @NotNull
    private Integer lateAttendanceCount;

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
    private String comments;

    @Nullable
    private Long essentialContentsId;

    @Nullable
    private Long additionalContentsId;

    @Nullable
    private Long limitedContentsId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    @JsonIgnore
    private Program program;

    @JsonIgnore
    @OneToMany(mappedBy = "mission", orphanRemoval = true)
    private List<Attendance> attendanceList;

    @Builder
    private Mission(Program program, MissionTopic topic, MissionType type, Integer refund, String title, String contents, String guide,
                    Integer th, LocalDateTime startDate, LocalDateTime endDate,
                    String template, String comments, Contents essentialContents, Contents additionalContents, Contents limitedContents) {
        this.program = program;
        this.type = type;
        this.topic = topic;

        this.refund = refund;
        this.attendanceCount = 0;
        this.lateAttendanceCount = 0;
        this.title = title;
        this.contents = contents;
        this.guide = guide;

        this.th = th;
        this.startDate = startDate;
        this.endDate = endDate;

        this.template = template;
        this.comments = comments;

        if(essentialContents != null) this.essentialContentsId = essentialContents.getId();
        if(additionalContents != null) this.additionalContentsId = additionalContents.getId();
        if(limitedContents != null) this.limitedContentsId = limitedContents.getId();
    }

    public static Mission of(Program program, MissionCreateDTO missionCreateDTO, Contents essentialContents, Contents additionalContents, Contents limitedContents) {
        return Mission.builder()
                .program(program)
                .type(missionCreateDTO.getType())
                .topic(missionCreateDTO.getTopic())
                .refund(missionCreateDTO.getRefund())
                .title(missionCreateDTO.getTitle())
                .contents(missionCreateDTO.getContents())
                .guide(missionCreateDTO.getGuide())
                .th(missionCreateDTO.getTh())
                .startDate(missionCreateDTO.getStartDate())
                .endDate(missionCreateDTO.getEndDate())
                .template(missionCreateDTO.getTemplate())
                .comments(missionCreateDTO.getComments())
                .essentialContents(essentialContents)
                .additionalContents(additionalContents)
                .limitedContents(limitedContents)
                .build();
    }
}
