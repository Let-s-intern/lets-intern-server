package com.letsintern.letsintern.domain.user.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdminUserVo {

    private Long id;
    private String name;
    private String email;
    private String phoneNum;
    private String university;
    private String major;
    private String signedUpAt;


    @Builder
    public AdminUserVo(Long id, String name, String email, String phoneNum, String university, String major, String signedUpAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.university = university;
        this.major = major;
        this.signedUpAt = signedUpAt;
    }
}
