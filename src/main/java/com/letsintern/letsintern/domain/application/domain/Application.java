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

    @NotNull Integer grade;

    @NotNull
    @Column(length = 100)
    String wishCompany;

    @NotNull
    @Column(length = 100)
    String wishJob;

    @NotNull
    String applyMotive;

    @Nullable
    String preQuestions;

    @NotNull
    @Enumerated(EnumType.STRING)
    InflowPath inflowPath;

    @NotNull
    private Boolean isApproved = false;

    @NotNull
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    @NotNull
    private Boolean attendance = true;

    @Nullable
    private Long reviewId;

    @NotNull
    private String createdAt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    // User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    @Nullable
    @JsonIgnore
    private User user = null;

    // Guest
    @Nullable
    @Size(max = 20)
    private String guestName;

    @Nullable
    @Column(length = 15)
    private String guestPhoneNum;

    @Nullable
    @Size(max = 255)
    private String guestEmail;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    @JsonIgnore
    Program program;


    @Builder
    private Application(Program program, User user, Integer grade, String wishCompany, String wishJob,
                             String applyMotive, String preQuestions, InflowPath inflowPath,
                             String guestName, String guestPhoneNum, String guestEmail) {
        this.program = program;
        this.user = user;
        this.grade = grade;
        this.wishCompany = wishCompany;
        this.wishJob = wishJob;
        this.applyMotive = applyMotive;
        this.preQuestions = preQuestions;
        this.inflowPath = inflowPath;
        this.guestName = guestName;
        this.guestPhoneNum = guestPhoneNum;
        this.guestEmail = guestEmail;
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
                .guestName(applicationCreateDTO.getGuestName())
                .guestPhoneNum(applicationCreateDTO.getGuestPhoneNum())
                .guestEmail(applicationCreateDTO.getGuestEmail())
                .build();
    }
}

