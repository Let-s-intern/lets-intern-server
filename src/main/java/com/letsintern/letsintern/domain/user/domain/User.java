package com.letsintern.letsintern.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpRequestDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at IS NULL")
@SQLDelete(sql = "UPDATE user SET deleted_at = CURRENT_TIMESTAMP where user_id = ?")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String email;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 128)
    @JsonIgnore
    private String password;

    @NotNull
    @Column(length = 15)
    private String phoneNum;

    @NotNull
    @Column(length = 10)
    private String signedUpAt;

    @Nullable
    @Column(length = 10)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate deletedAt;

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
    private Long managerId;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Application> applicationList;


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

    public static User of(UserSignUpRequestDTO userSignUpRequestDTO, String encodedPassword) {
        return User.builder()
                .email(userSignUpRequestDTO.getUserVo().getEmail())
                .name(userSignUpRequestDTO.getUserVo().getName())
                .password(encodedPassword)
                .phoneNum(userSignUpRequestDTO.getUserVo().getPhoneNum())
                .build();
    }
}

