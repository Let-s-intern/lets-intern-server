package com.letsintern.letsintern.domain.user.dto.request;

import lombok.Getter;

@Getter
public class UserUpdateRequestDTO {

    private String name;
    private String email;
    private String phoneNum;
    private String university;
    private String major;
}
