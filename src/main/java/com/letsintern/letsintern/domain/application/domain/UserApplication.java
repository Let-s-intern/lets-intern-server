package com.letsintern.letsintern.domain.application.domain;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserApplication extends Application {

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Builder
    private UserApplication(Program program, User user, Integer grade, String wishCompany,
                            String wishJob, String applyMotive, String preQuestions) {
        this.program = program;
        this.user = user;
        this.grade = grade;
        this.wishCompany = wishCompany;
        this.wishJob = wishJob;
        this.applyMotive = applyMotive;
        this.preQuestions = preQuestions;
    }

    public static UserApplication of(Program program, User user, ApplicationCreateDTO applicationCreateDTO) {
        return UserApplication.builder()
                .program(program)
                .user(user)
                .grade(applicationCreateDTO.getGrade())
                .wishCompany(applicationCreateDTO.getWishCompany())
                .wishJob(applicationCreateDTO.getWishJob())
                .applyMotive(applicationCreateDTO.getApplyMotive())
                .preQuestions(applicationCreateDTO.getPreQuestions())
                .build();
    }
}
