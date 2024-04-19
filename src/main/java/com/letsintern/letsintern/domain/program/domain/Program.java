package com.letsintern.letsintern.domain.program.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.mission.domain.Mission;
import com.letsintern.letsintern.domain.notice.domain.Notice;
import com.letsintern.letsintern.domain.program.dto.request.ProgramCreateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.request.ProgramUpdateRequestDTO;
import com.letsintern.letsintern.domain.program.dto.response.ZoomMeetingCreateResponse;
import com.letsintern.letsintern.domain.user.domain.AccountType;
import com.letsintern.letsintern.global.common.util.StringUtils;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.letsintern.letsintern.global.utils.EntityUpdateValueUtils.updateValue;

@Entity
@Getter
@Setter
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
    private Integer feeRefund = 0;

    @NotNull
    private Integer feeCharge = 0;

    @NotNull
    private Integer discountValue = 0;

    @Nullable
    private LocalDateTime feeDueDate;

    @Nullable
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Nullable
    private String accountNumber;

    /* Lets Chat */
    @Nullable
    private String mentorPassword;

    /* Challenge */
    @Nullable
    @Enumerated(EnumType.STRING)
    private ProgramTopic topic;

    @Nullable
    private String openKakaoLink;

    @Nullable
    @Column(length = 10)
    private String openKakaoPassword;

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
                    ProgramFeeType feeType, Integer feeRefund, Integer feeCharge, Integer discountValue, LocalDateTime feeDueDate, AccountType accountType, String accountNumber, String mentorPassword,
                    ProgramTopic topic, String openKakaoLink, String openKakaoPassword, ZoomMeetingCreateResponse zoomMeetingCreateResponse) {
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
        if (feeType.equals(ProgramFeeType.CHARGE) || feeType.equals(ProgramFeeType.REFUND)) {
            if (feeType.equals(ProgramFeeType.REFUND)) this.feeRefund = feeRefund;
            this.feeCharge = feeCharge;
            this.feeDueDate = feeDueDate;
            this.discountValue = discountValue;
            this.accountType = accountType;
            this.accountNumber = accountNumber;
        }

        // Zoom Link
        if ((way.equals(ProgramWay.ONLINE) || way.equals(ProgramWay.ALL)) && zoomMeetingCreateResponse != null) {
            this.link = zoomMeetingCreateResponse.getJoin_url();
            this.linkPassword = zoomMeetingCreateResponse.getPassword();
        }

        // CHALLENGE
        if (type.equals(ProgramType.CHALLENGE_HALF) || type.equals(ProgramType.CHALLENGE_FULL)) {
            this.topic = topic;
            this.openKakaoLink = openKakaoLink;
            this.openKakaoPassword = openKakaoPassword;
        }

        // LETS_CHAT
        if (type.equals(ProgramType.LETS_CHAT)) {
            this.mentorPassword = mentorPassword;
            this.mailStatus = MailStatus.YET;
        }
    }

    public static Program of(ProgramCreateRequestDTO programCreateRequestDTO, String mentorPassword, ZoomMeetingCreateResponse zoomMeetingCreateResponse) {
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
                .feeRefund(programCreateRequestDTO.getFeeRefund())
                .feeCharge(programCreateRequestDTO.getFeeCharge())
                .discountValue(programCreateRequestDTO.getDiscountValue())
                .feeDueDate(programCreateRequestDTO.getFeeDueDate())
                .accountType(programCreateRequestDTO.getAccountType())
                .accountNumber(programCreateRequestDTO.getAccountNumber())
                .mentorPassword(mentorPassword)
                .topic(programCreateRequestDTO.getTopic())
                .openKakaoLink(programCreateRequestDTO.getOpenKakaoLink())
                .openKakaoPassword(programCreateRequestDTO.getOpenKakaoPassword())
                .zoomMeetingCreateResponse(zoomMeetingCreateResponse)
                .build();
    }

    public void increaseProgramApplicationCount() {
        this.applicationCount++;
    }

    public void decreaseProgramApplicationCount() {
        this.applicationCount--;
    }

    public void updateProgramInfo(ProgramUpdateRequestDTO programUpdateRequestDTO,
                                  ProgramStatus programStatus,
                                  String stringFaqList) {
        this.type = updateValue(this.type, programUpdateRequestDTO.getType());
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
        this.link = updateValue(this.link, programUpdateRequestDTO.getLink());
        this.linkPassword = updateValue(this.linkPassword, programUpdateRequestDTO.getLinkPassword());
        this.feeType = updateValue(this.feeType, programUpdateRequestDTO.getFeeType());
        this.feeRefund = updateValue(this.feeRefund, programUpdateRequestDTO.getFeeRefund());
        this.feeCharge = updateValue(this.feeCharge, programUpdateRequestDTO.getFeeCharge());
        this.discountValue = updateValue(this.discountValue, programUpdateRequestDTO.getDiscountValue());
        this.feeDueDate = updateValue(this.feeDueDate, programUpdateRequestDTO.getFeeDueDate());
        this.accountType = updateValue(this.accountType, programUpdateRequestDTO.getAccountType());
        this.accountNumber = updateValue(this.accountNumber, programUpdateRequestDTO.getAccountNumber());
        this.topic = updateValue(this.topic, programUpdateRequestDTO.getTopic());
        this.openKakaoLink = updateValue(this.openKakaoLink, programUpdateRequestDTO.getOpenKakaoLink());
        this.openKakaoPassword = updateValue(this.openKakaoPassword, programUpdateRequestDTO.getOpenKakaoPassword());
    }

}
