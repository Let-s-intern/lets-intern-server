package com.letsintern.letsintern.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.text.SimpleDateFormat;
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
    private Integer prepareForEmployment;       // 네, 아니오, 잘 모르겠어요

    @NotNull
    private String joinedAt;

    @NotNull
    private String role;

    @Builder
    private User(String name, String email, String phoneNum,
                 String university, String major, Integer grade,
                 String inflow, String wishCompany, String wishJob,
                 Integer prepareForEmployment) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.university = university;
        this.major = major;
        this.grade = grade;
        this.inflow = inflow;
        this.wishCompany = wishCompany;
        this.wishJob = wishJob;
        this.prepareForEmployment = prepareForEmployment;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.joinedAt = simpleDateFormat.format(new Date());
        this.role = "QUEST";
    }

    public static User of(UserSignUpDTO userSignUpDTO) {
        return User.builder()
                .name(userSignUpDTO.getName())
                .email(userSignUpDTO.getEmail())
                .phoneNum(userSignUpDTO.getPhoneNum())
                .university(userSignUpDTO.getUniversity())
                .major(userSignUpDTO.getMajor())
                .grade(userSignUpDTO.getGrade())
                .inflow(userSignUpDTO.getInflow())
                .wishCompany(userSignUpDTO.getWishCompany())
                .wishJob(userSignUpDTO.getWishJob())
                .prepareForEmployment(userSignUpDTO.getPrepareForEmployment())
                .build();
    }
}

