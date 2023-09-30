package com.letsintern.letsintern.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 20)
    private String name;

    @NotNull
    @Column(length = 20)
    private String email;

    @NotNull
    @Column(unique = true, length = 15)
    private String phoneNum;

    @NotNull
    @Column(length = 50)
    private String university;

    @NotNull
    @Column(length = 50)
    private String major;

    @NotNull
    private Integer grade;

    @NotNull
    @Column(length = 100)
    private String inflow;

    @NotNull
    @Column(length = 100)
    private String wishCompany;

    @NotNull
    @Column(length = 100)
    private String wishJob;

    @NotNull
    private Boolean prepareForEmployment;

    @NotNull
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date joinedAt;

}