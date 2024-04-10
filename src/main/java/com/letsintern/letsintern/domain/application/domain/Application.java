package com.letsintern.letsintern.domain.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.payment.domain.FeeType;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
import com.letsintern.letsintern.domain.user.domain.AccountType;
import com.letsintern.letsintern.domain.user.domain.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Where;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application {

    @Id
    @Column(name = "application_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer grade;

    @NotNull
    @Column(length = 100)
    private String wishCompany;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ApplicationWishJob wishJob;

    @NotNull
    @Size(max = 700)
    private String applyMotive;

    @Nullable
    @Size(max = 300)
    private String preQuestions;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private InflowPath inflowPath;

    @Nullable
    @Size(max = 20)
    private String name;

    @Nullable
    @Column(length = 15)
    private String phoneNum;

    @Nullable
    @Size(max = 30)
    private String email;

    @Nullable
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Nullable
    @Column(length = 20)
    private String accountNumber;

    @NotNull
    @Column(length = 10)
    private ApplicationType type;

    @Nullable
    @Column(length = 10)
    private ApplicationWay way;

    @Nullable
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    @JsonIgnore
    @Where(clause = "deleted_at != NULL")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    @JsonIgnore
    private Program program;


    /* Default */
    @NotNull
    private Boolean isApproved = false;

    @NotNull
    private Boolean feeIsConfirmed = false;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    @NotNull
    private ApplicationAttendance attendance = ApplicationAttendance.ATTENDED;

    @NotNull
    private String createdAt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    @Nullable
    private String introduction;

    @Nullable
    private String couponCode;
    private Integer totalFee;

    @Builder
    private Application(Program program, User user, Integer grade, String wishCompany, ApplicationWishJob wishJob,
                        String applyMotive, String preQuestions, InflowPath inflowPath,
                        String name, String phoneNum, String email, AccountType accountType, String accountNumber, ApplicationWay way, String couponCode, Integer totalFee) {
        this.program = program;
        this.user = user;
        this.grade = grade;
        this.wishCompany = wishCompany;
        this.wishJob = wishJob;
        this.applyMotive = applyMotive;
        this.preQuestions = preQuestions;
        this.inflowPath = inflowPath;
        this.couponCode = couponCode;
        this.totalFee = totalFee;
        if (way != null) this.way = way;

        /* 비회원 */
        if (user == null) {
            this.type = ApplicationType.GUEST;
            this.name = name;
            this.phoneNum = phoneNum;
            this.email = email;

            if (program.getPayment().getFeeType().equals(FeeType.REFUND)) {
                this.accountType = accountType;
                this.accountNumber = accountNumber;
            }
        }

        /* 회원 */
        else {
            this.type = ApplicationType.USER;

            if (program.getProgramType().equals(ProgramType.CHALLENGE_FULL) || program.getProgramType().equals(ProgramType.CHALLENGE_HALF)) {
                this.introduction = "안녕하세요, " + user.getName() + "입니다";
            }
        }
    }

    public static Application of(Program program, User user, ApplicationCreateDTO applicationCreateDTO, Integer totalFee) {
        return Application.builder()
                .program(program)
                .user(user)
                .grade(applicationCreateDTO.getGrade())
                .wishCompany(applicationCreateDTO.getWishCompany())
                .wishJob(applicationCreateDTO.getWishJob())
                .applyMotive(applicationCreateDTO.getApplyMotive())
                .preQuestions(applicationCreateDTO.getPreQuestions())
                .inflowPath(applicationCreateDTO.getInflowPath())
                .name(applicationCreateDTO.getGuestName())
                .phoneNum(applicationCreateDTO.getGuestPhoneNum())
                .email(applicationCreateDTO.getGuestEmail())
                .accountType(applicationCreateDTO.getAccountType())
                .accountNumber(applicationCreateDTO.getAccountNumber())
                .way(applicationCreateDTO.getWay())
                .couponCode(applicationCreateDTO.getCode())
                .totalFee(totalFee)
                .build();
    }
}

