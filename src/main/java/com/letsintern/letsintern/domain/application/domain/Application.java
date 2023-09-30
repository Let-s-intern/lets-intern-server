package com.letsintern.letsintern.domain.application.domain;

import com.letsintern.letsintern.domain.application.dto.request.ApplicationCreateDTO;
import com.letsintern.letsintern.domain.program.domain.Program;
import com.letsintern.letsintern.domain.user.domain.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String applyMotive;

    @Nullable
    private String question;

    @NotNull
    private Boolean checkAttendance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Builder
    private Application(Program program, User user,
                        String applyMotive, String question) {
        this.program = program;
        this.user = user;
        this.applyMotive = applyMotive;
        this.question = question;
        this.checkAttendance = false;
    }

    public static Application of(Program program, User user, ApplicationCreateDTO applicationCreateDTO) {
        return Application.builder()
                .program(program)
                .user(user)
                .applyMotive(applicationCreateDTO.getApplyMotive())
                .question(applicationCreateDTO.getQuestion())
                .build();
    }
}
