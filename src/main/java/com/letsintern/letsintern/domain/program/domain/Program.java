package com.letsintern.letsintern.domain.program.domain;

import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.payment.domain.Payment;
import com.letsintern.letsintern.domain.program.domain.converter.ProgramStatusConverter;
import com.letsintern.letsintern.domain.program.domain.converter.ProgramTypeConverter;
import com.letsintern.letsintern.domain.program.domain.converter.ProgramWayConverter;
import com.letsintern.letsintern.domain.program.dto.request.ProgramRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;
import static com.letsintern.letsintern.global.utils.EnumValueUtils.toEntityCode;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
    @Builder.Default
    private Integer headcount = 0;
    @Column(nullable = false)
    @Builder.Default
    private Boolean isVisible = false;
    @Column(nullable = false)
    @Convert(converter = ProgramWayConverter.class)
    private ProgramWay way;
    @Column(nullable = false)
    private String faqListStr;
    private String location;
    private String zoomLink;
    @Column(length = 8)
    private String zoomLinkPassword;
    @Column(length = 10, nullable = false)
    @Convert(converter = ProgramStatusConverter.class)
    @Builder.Default
    private ProgramStatus status = ProgramStatus.OPEN;
    @Column(length = 20, nullable = false)
    @Convert(converter = ProgramTypeConverter.class)
    private ProgramType programType;
    @OneToMany(mappedBy = "program", orphanRemoval = true)
    @Builder.Default
    private List<Application> applicationList = new ArrayList<>();
    @OneToOne
    private Payment payment;

    public Program(ProgramRequestDto requestDto, String zoomLink, String zoomLinkPassword) {
        this.title = requestDto.title();
        this.contents = requestDto.contents();
        this.notice = requestDto.notice();
        this.th = requestDto.th();
        this.announcementDate = requestDto.announcementDate();
        this.dueDate = requestDto.dueDate();
        this.startDate = requestDto.startDate();
        this.endDate = requestDto.endDate();
        this.headcount = requestDto.headcount();
        this.way = toEntityCode(ProgramWay.class, requestDto.way());
        this.faqListStr = requestDto.faqIdList().toString();
        this.location = requestDto.location();
        this.isVisible = requestDto.isVisible();
        this.programType = toEntityCode(ProgramType.class, requestDto.programType());
        this.zoomLink = zoomLink;
        this.zoomLinkPassword = zoomLinkPassword;
    }

    public void addPayment(Payment payment) {
        this.payment = payment;
    }

    public void increaseProgramApplicationCount() {
        this.applicationCount++;
    }

    public void decreaseProgramApplicationCount() {
        this.applicationCount--;
    }

    public void updateHeadCount(Integer headcount) {
        this.headcount = headcount;
    }

    public void updateProgramInfo(ProgramRequestDto programRequestDto,
                                  ProgramStatus programStatus,
                                  String stringFaqList) {
        this.th = updateValue(this.th, programRequestDto.th());
        this.title = updateValue(this.title, programRequestDto.title());
        this.headcount = updateValue(this.headcount, programRequestDto.headcount());
        this.dueDate = updateValue(this.dueDate, programRequestDto.dueDate());
        this.announcementDate = updateValue(this.announcementDate, programRequestDto.announcementDate());
        this.startDate = updateValue(this.startDate, programRequestDto.startDate());
        this.endDate = updateValue(this.endDate, programRequestDto.endDate());
        this.contents = updateValue(this.contents, programRequestDto.contents());
        this.way = updateValue(this.way, toEntityCode(ProgramWay.class, programRequestDto.way()));
        this.location = updateValue(this.location, programRequestDto.location());
        this.notice = updateValue(this.notice, programRequestDto.notice());
        this.status = updateValue(this.status, programStatus);
        this.isVisible = updateValue(this.isVisible, programRequestDto.isVisible());
        this.faqListStr = updateValue(this.faqListStr, stringFaqList);
        this.programType = updateValue(this.programType, toEntityCode(ProgramType.class, programRequestDto.programType()));
    }
}