package com.letsintern.letsintern.domain.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.program.domain.Program;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
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


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "program_id", nullable = false)
    @JsonIgnore
    Program program;

}

