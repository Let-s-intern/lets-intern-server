package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.program.domain.converter.ProgramStatusConverter;
import com.letsintern.letsintern.domain.program.domain.converter.ProgramWayConverter;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Getter
@Table(name = "program")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class Program {
    @Id
    @Column(name = "program_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 300, nullable = false)
    private String contents;
    @Column(length = 300, nullable = false)
    private String notice;
    @Column(nullable = false)
    private Integer th;
    @Column(nullable = false)
    private LocalDateTime announcementDate;
    @Column(nullable = false)
    private LocalDateTime dueDate;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;
    @Column(nullable = false)
    @Builder.Default
    private Integer applicationCount = 0;
    @Column(nullable = false)
    private Integer headcount;
    @Column(nullable = false)
    @Builder.Default
    private Boolean isVisible = false;
    @Column(nullable = false)
    @Convert(converter = ProgramWayConverter.class)
    private ProgramWay way;
    @Column(nullable = false)
    private String faqListStr;
    private String location;
    @Column(length = 10, nullable = false)
    @Convert(converter = ProgramStatusConverter.class)
    @Builder.Default
    private ProgramStatus status = ProgramStatus.OPEN;
    @OneToMany(mappedBy = "program", orphanRemoval = true)
    @Builder.Default
    private List<Application> applicationList = new ArrayList<>();

    public void increaseProgramApplicationCount() {
        this.applicationCount++;
    }

    public void updateProgramInfo(ProgramUpdateRequestDTO programUpdateRequestDTO,
                                  ProgramStatus programStatus,
                                  String stringFaqList) {
        this.th = updateValue(this.th, programUpdateRequestDTO.getTh());
        this.title = updateValue(this.title, programUpdateRequestDTO.getTitle());
        this.headcount = updateValue(this.headcount, programUpdateRequestDTO.getHeadcount());
        this.dueDate = updateValue(this.dueDate, programUpdateRequestDTO.getDueDate());
        this.announcementDate = updateValue(this.announcementDate, programUpdateRequestDTO.getAnnouncementDate());
        this.startDate = updateValue(this.startDate, programUpdateRequestDTO.getStartDate());
        this.endDate = updateValue(this.endDate, programUpdateRequestDTO.getEndDate());
        this.contents = updateValue(this.contents, programUpdateRequestDTO.getContents());
        this.way = updateValue(this.way, programUpdateRequestDTO.getWay());
        this.location = updateValue(this.location, programUpdateRequestDTO.getLocation());
        this.notice = updateValue(this.notice, programUpdateRequestDTO.getNotice());
        this.status = updateValue(this.status, programStatus);
        this.isVisible = updateValue(this.isVisible, programUpdateRequestDTO.getIsVisible());
        this.faqListStr = updateValue(this.faqListStr, stringFaqList);
    }
}
