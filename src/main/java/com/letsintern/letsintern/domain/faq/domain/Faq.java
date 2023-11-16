package com.letsintern.letsintern.domain.faq.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.program.domain.Program;
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
    private String question;

    @NotNull
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    @JsonIgnore
    Program program;

    @Builder
    private Faq(Program program, String question, String answer) {
        this.program = program;
        this.question = question;
        this.answer = answer;
    }

    public static Faq of(Program program, String question, String answer) {
        return Faq.builder()
                .program(program)
                .question(question)
                .answer(answer)
                .build();
    }

}
