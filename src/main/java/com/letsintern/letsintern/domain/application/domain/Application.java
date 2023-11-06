package com.letsintern.letsintern.domain.application.domain;

import com.letsintern.letsintern.domain.program.domain.Program;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Application {

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

    @NotNull
    private Boolean approved = false;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    Program program;

}

