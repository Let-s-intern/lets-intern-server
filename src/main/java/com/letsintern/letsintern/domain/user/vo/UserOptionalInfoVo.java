package com.letsintern.letsintern.domain.user.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserOptionalInfoVo {

    private String university;
    private String major;

    @Builder
    private UserOptionalInfoVo(String university, String major) {
        this.university = university;
        this.major = major;
    }

    public static UserOptionalInfoVo of(String university, String major) {
        return UserOptionalInfoVo.builder()
                .university(university)
                .major(major)
                .build();
    }
}
