package com.letsintern.letsintern.domain.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.user.domain.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Getter @Setter
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
    @Column(length = 100)
    private String wishJob;

    @NotNull
    private String applyMotive;

    @Nullable
    private String preQuestions;

    @NotNull
    @Enumerated(EnumType.STRING)
    private InflowPath inflowPath;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Column(length = 15)
    private String phoneNum;

    @NotNull
    @Size(max = 30)
    private String email;

    @NotNull
    private ApplicationType type;

    @Nullable
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    @JsonIgnore
    private User user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    @JsonIgnore
    private Program program;



    /* Default */
    @NotNull
    private Boolean isApproved = false;

    @NotNull
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    @NotNull
    private ApplicationAttendance attendance = ApplicationAttendance.ATTENDED;

    @NotNull
    private String createdAt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());



    @Builder
    private Application(Program program, User user, Integer grade, String wishCompany, String wishJob,
                             String applyMotive, String preQuestions, InflowPath inflowPath,
                             String name, String phoneNum, String email) {
        this.program = program;
        this.user = user;
        this.grade = grade;
        this.wishCompany = wishCompany;
        this.wishJob = wishJob;
        this.applyMotive = applyMotive;
        this.preQuestions = preQuestions;
        this.inflowPath = inflowPath;

        /* 비회원 */
        if(user == null) {
            this.type = ApplicationType.GUEST;
            this.name = name;
            this.phoneNum = phoneNum;
            this.email = email;
        }

        /* 회원 */
        else {
            this.type = ApplicationType.USER;
            this.name = user.getName();
            this.phoneNum = user.getPhoneNum();
            this.email = user.getEmail();
        }
    }

    public static Application of(Program program, User user, ApplicationCreateDTO applicationCreateDTO) {
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
                .build();
    }
}

