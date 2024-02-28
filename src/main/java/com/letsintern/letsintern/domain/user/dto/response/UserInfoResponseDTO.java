package com.letsintern.letsintern.domain.user.dto.response;

import com.letsintern.letsintern.domain.user.domain.AccountType;
import com.letsintern.letsintern.domain.user.domain.User;
import com.letsintern.letsintern.domain.user.oauth2.AuthProvider;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoResponseDTO {

    private String name;
    private String email;
    private String phoneNum;
    private String university;
    private String major;
    private AuthProvider authProvider;
    private AccountType accountType;
    private String accountNumber;

    @Builder
    private UserInfoResponseDTO(String name, String email, String phoneNum, String university, String major, AuthProvider authProvider, AccountType accountType, String accountNumber) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.university = university;
        this.major = major;
        this.authProvider = authProvider;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }

    public static UserInfoResponseDTO from(User user) {
        return UserInfoResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phoneNum(user.getPhoneNum())
                .university(user.getUniversity())
                .major(user.getMajor())
                .authProvider(user.getAuthProvider())
                .accountType(user.getAccountType())
                .accountNumber(user.getAccountNumber())
                .build();
    }
}
