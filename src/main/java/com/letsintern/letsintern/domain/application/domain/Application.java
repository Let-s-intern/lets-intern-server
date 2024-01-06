package com.letsintern.letsintern.domain.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.program.domain.ProgramType;
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
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    @NotNull
    private ApplicationAttendance attendance = ApplicationAttendance.ATTENDED;

    @NotNull
    private String createdAt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());



    @Builder
    private Application(Program program, User user, Integer grade, String wishCompany, String wishJob,
                             String applyMotive, String preQuestions, InflowPath inflowPath,
                             String name, String phoneNum, String email, ApplicationWay way) {
        this.program = program;
        this.user = user;
        this.grade = grade;
        this.wishCompany = wishCompany;
        this.wishJob = wishJob;
        this.applyMotive = applyMotive;
        this.preQuestions = preQuestions;
        this.inflowPath = inflowPath;
        if(way != null) this.way = way;

        if(program.getType().equals(ProgramType.LETS_CHAT)) this.status = ApplicationStatus.IN_PROGRESS;

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
                .way(applicationCreateDTO.getWay())
                .build();
    }
}

