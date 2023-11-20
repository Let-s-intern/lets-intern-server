package com.letsintern.letsintern.domain.application.domain;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.program.domain.Program;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@DiscriminatorValue("quest")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestApplication extends Application {

    @Nullable
    @Size(max = 20)
    private String guestName;

    @Nullable
    @Column(length = 15)
    private String guestPhoneNum;

    @Nullable
    @Size(max = 255)
    private String guestEmail;

    @Builder
    private GuestApplication(Program program, Integer grade, String wishCompany, String wishJob, String applyMotive, String preQuestions,
                             String guestName, String guestPhoneNum, String guestEmail) {
        this.program = program;
        this.grade = grade;
        this.wishCompany = wishCompany;
        this.wishJob = wishJob;
        this.applyMotive = applyMotive;
        this.preQuestions = preQuestions;
        this.guestName = guestName;
        this.guestPhoneNum = guestPhoneNum;
        this.guestEmail = guestEmail;
    }

    public static GuestApplication of(Program program, ApplicationCreateDTO applicationCreateDTO) {
        return GuestApplication.builder()
                .program(program)
                .grade(applicationCreateDTO.getGrade())
                .wishCompany(applicationCreateDTO.getWishCompany())
                .wishJob(applicationCreateDTO.getWishJob())
                .applyMotive(applicationCreateDTO.getApplyMotive())
                .preQuestions(applicationCreateDTO.getPreQuestions())
                .guestName(applicationCreateDTO.getGuestName())
                .guestPhoneNum(applicationCreateDTO.getGuestPhoneNum())
                .guestEmail(applicationCreateDTO.getGuestEmail())
                .build();
    }
}
