package com.letsintern.letsintern.domain.faq.domain;

import com.letsintern.letsintern.domain.program.domain.ProgramType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Faq {

    @Id
    @Column(name = "faq_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private ProgramType programType;

    @NotNull
    private String question;

    @NotNull
    private String answer;

    @Builder
    private Faq(ProgramType programType, String question, String answer) {
        this.programType = programType;
        this.question = question;
        this.answer = answer;
    }

    public static Faq of(ProgramType programType, String question, String answer) {
        return Faq.builder()
                .programType(programType)
                .question(question)
                .answer(answer)
                .build();
    }

}
