package com.letsintern.letsintern.domain.program.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ZoomMeetingCreateResponse;
import com.letsintern.letsintern.domain.user.domain.AccountType;
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
public class Program {

    @Id
    @Column(name = "program_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private ProgramType type;

    @NotNull
    private Integer th;

    @NotNull
    @Column(length = 50)
    private String title;

    @NotNull
    private Integer headcount;

    @NotNull
    private Integer applicationCount = 0;

    @NotNull
    private Integer finalHeadCount = 0;

    @NotNull
    private LocalDateTime dueDate;

    @NotNull
    private LocalDateTime announcementDate;

    @NotNull
    private LocalDateTime startDate;

    @NotNull
    private LocalDateTime endDate;

    @NotNull
    @Column(length = 300)
    private String contents;

    @NotNull
    private ProgramWay way;

    @Nullable
    private String location;

    @Nullable
    private String link;

    @Nullable
    @Column(length = 8)
    private String linkPassword;

    @NotNull
    @Column(length = 300)
    private String notice;

    @NotNull
    private String faqListStr;

    @NotNull
    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private ProgramStatus status = ProgramStatus.OPEN;

    @Nullable
    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private MailStatus mailStatus;

    @NotNull
    private Boolean isVisible = false;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProgramFeeType feeType;

    @NotNull
    private Integer feeTotal = 0;

    @Nullable
    private LocalDateTime feeDueDate;

    @Nullable
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Nullable
    private String accountNumber;


    /* Challenge */
    @Nullable
    @Enumerated(EnumType.STRING)
    private ProgramTopic topic;

    @Nullable
    private String openKakaoLink;


    @JsonIgnore
    @OneToMany(mappedBy = "program", orphanRemoval = true)
    private List<Application> applicationList;

    @JsonIgnore
    @OneToMany(mappedBy = "program", orphanRemoval = true)
    private List<Mission> missionList;

    @JsonIgnore
    @OneToMany(mappedBy = "program", orphanRemoval = true)
    private List<Notice> noticeList;

    @Builder
    private Program(ProgramType type, Integer th, String title, Integer headcount,
                    LocalDateTime dueDate, LocalDateTime announcementDate, LocalDateTime startDate, LocalDateTime endDate,
                    String contents, ProgramWay way, String location, String notice, List<Long> faqIdList,
                    ProgramFeeType feeType, Integer feeTotal, LocalDateTime feeDueDate, AccountType accountType, String accountNumber,
                    ProgramTopic topic, String openKakaoLink, ZoomMeetingCreateResponse zoomMeetingCreateResponse) {
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
        this.notice = notice;
        this.faqListStr = StringUtils.listToString(faqIdList);
        this.feeType = feeType;

        // 이용료 or 보증금 프로그램
        if(feeType.equals(ProgramFeeType.CHARGE) || feeType.equals(ProgramFeeType.REFUND)) {
            this.feeTotal = feeTotal;
            this.feeDueDate = feeDueDate;
            this.accountType = accountType;
            this.accountNumber = accountNumber;
        }

        // Zoom Link
        if((way.equals(ProgramWay.ONLINE) || way.equals(ProgramWay.ALL)) && zoomMeetingCreateResponse != null) {
            this.link = zoomMeetingCreateResponse.getJoin_url();
            this.linkPassword = zoomMeetingCreateResponse.getPassword();
        }

        // CHALLENGE
        if(type.equals(ProgramType.CHALLENGE_HALF) || type.equals(ProgramType.CHALLENGE_FULL)) {
            this.topic = topic;
            this.openKakaoLink = openKakaoLink;
        }

        // LETS_CHAT
        if(type.equals(ProgramType.LETS_CHAT)) {
            this.mailStatus = MailStatus.YET;
        }
    }

    public static Program of(ProgramCreateRequestDTO programCreateRequestDTO, ZoomMeetingCreateResponse zoomMeetingCreateResponse) {
        return Program.builder()
                .type(programCreateRequestDTO.getType())
                .th(programCreateRequestDTO.getTh())
                .title(programCreateRequestDTO.getTitle())
                .headcount(programCreateRequestDTO.getHeadcount())
                .dueDate(programCreateRequestDTO.getDueDate())
                .announcementDate(programCreateRequestDTO.getAnnouncementDate())
                .startDate(programCreateRequestDTO.getStartDate())
                .endDate(programCreateRequestDTO.getEndDate())
                .contents(programCreateRequestDTO.getContents())
                .way(programCreateRequestDTO.getWay())
                .location(programCreateRequestDTO.getLocation())
                .notice(programCreateRequestDTO.getNotice())
                .faqIdList(programCreateRequestDTO.getFaqIdList())
                .feeType(programCreateRequestDTO.getFeeType())
                .feeTotal(programCreateRequestDTO.getFeeTotal())
                .feeDueDate(programCreateRequestDTO.getFeeDueDate())
                .accountType(programCreateRequestDTO.getAccountType())
                .accountNumber(programCreateRequestDTO.getAccountNumber())
                .topic(programCreateRequestDTO.getTopic())
                .openKakaoLink(programCreateRequestDTO.getOpenKakaoLink())
                .zoomMeetingCreateResponse(zoomMeetingCreateResponse)
                .build();
    }
}
