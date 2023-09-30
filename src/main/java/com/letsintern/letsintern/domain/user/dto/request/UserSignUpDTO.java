package com.letsintern.letsintern.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignUpDTO {

    private String name;

    private String email;

    private String phoneNum;

    private String university;

    private String major;

    private Integer grade;

    private String inflow;

    private String wishCompany;

    private String wishJob;

    private Integer prepareForEmployment;
}
