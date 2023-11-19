package com.letsintern.letsintern.domain.user.dto.response;

import com.letsintern.letsintern.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoResponseDTO {

    private String name;
    private String email;
    private String phoneNum;
    private String university;
    private String major;

    @Builder
    private UserInfoResponseDTO(String name, String email, String phoneNum, String university, String major) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.university = university;
        this.major = major;
    }

    public static UserInfoResponseDTO from(User user) {
        return UserInfoResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phoneNum(user.getPhoneNum())
                .university(user.getUniversity())
                .major(user.getMajor())
                .build();
    }
}
