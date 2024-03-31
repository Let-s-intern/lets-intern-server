package com.letsintern.letsintern.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.letsintern.letsintern.domain.application.domain.Application;
import com.letsintern.letsintern.domain.coupon.domain.CouponUser;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpRequestDTO;
import com.letsintern.letsintern.domain.user.oauth2.AuthProvider;
import com.letsintern.letsintern.domain.user.oauth2.user.OAuth2UserInfo;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
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
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate signedUpAt;

    @Nullable
    @Column(length = 10)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate deletedAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Nullable
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Nullable
    @Column(length = 50)
    private String university;

    @Nullable
    @Column(length = 50)
    private String major;

    @Nullable
    private Long managerId;

    @Nullable
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Nullable
    @Column(length = 20)
    private String accountNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Application> applicationList = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<CouponUser> couponUserList = new ArrayList<>();


//    @Builder
//    private User(String email, String name, String password, String phoneNum,
//                 AuthProvider authProvider) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.phoneNum = phoneNum;
//
//        if(authProvider != null) this.authProvider = authProvider;
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        this.signedUpAt = simpleDateFormat.format(new Date());
//        this.role = UserRole.ROLE_ANONYMOUS;
//        this.couponUserList = new ArrayList<>();
//    }

    public static User of(UserSignUpRequestDTO userSignUpRequestDTO, String encodedPassword) {
        return User.builder()
                .email(userSignUpRequestDTO.getUserVo().getEmail())
                .name(userSignUpRequestDTO.getUserVo().getName())
                .password(encodedPassword)
                .phoneNum(userSignUpRequestDTO.getUserVo().getPhoneNum())
                .build();
    }

    public static User ofOAuth(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        return User.builder()
                .email(oAuth2UserInfo.getEmail())
                .name(oAuth2UserInfo.getName())
                .password(oAuth2UserInfo.getOAuth2Id())
                .phoneNum(oAuth2UserInfo.getPhoneNum())
                .authProvider(authProvider)
                .build();
    }

    public User updateFrom(OAuth2UserInfo oAuth2UserInfo) {
        this.password = oAuth2UserInfo.getOAuth2Id();
        this.name = oAuth2UserInfo.getName();
        this.email = oAuth2UserInfo.getEmail();
        this.phoneNum = oAuth2UserInfo.getPhoneNum();
        return this;
    }

    public void addCouponUserList(CouponUser couponUser) {
        this.couponUserList.add(couponUser);
    }

    public void updateUniversity(String university) {
        this.university = university;
    }

    public void updateMajor(String major) {
        this.major = major;
    }

    public void updateAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void updateAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

