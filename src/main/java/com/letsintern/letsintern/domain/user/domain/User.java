package com.letsintern.letsintern.domain.user.domain;

import com.letsintern.letsintern.domain.user.dto.request.UserSignUpRequest;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 128)
    private String password;

    @NotNull
    @Column(unique = true, length = 15)
    private String phoneNum;

    @NotNull
    private String signedUpAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;


    @Nullable
    @Column(length = 50)
    private String university;

    @Nullable
    @Column(length = 50)
    private String major;

    @Nullable
    private Integer grade;

    @Nullable
    @Column(length = 100)
    private String wishCompany;

    @Nullable
    @Column(length = 100)
    private String wishJob;


    @Builder
    private User(String email, String name, String password, String phoneNum) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.signedUpAt = simpleDateFormat.format(new Date());
        this.role = UserRole.ROLE_ANONYMOUS;
    }

    public static User of(UserSignUpRequest userSignUpRequest, String encodedPassword) {
        return User.builder()
                .email(userSignUpRequest.getUserVo().getEmail())
                .name(userSignUpRequest.getUserVo().getName())
                .password(encodedPassword)
                .phoneNum(userSignUpRequest.getUserVo().getPhoneNum())
                .build();
    }
}

