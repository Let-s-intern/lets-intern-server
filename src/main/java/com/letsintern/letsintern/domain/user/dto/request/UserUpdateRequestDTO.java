package com.letsintern.letsintern.domain.user.dto.request;

import com.letsintern.letsintern.domain.user.domain.UserRole;
import lombok.Getter;

@Getter
public class UserUpdateRequestDTO {

    private String name;
    private String email;
    private String phoneNum;
    private String university;
    private String major;

    private Long managerId;
    private UserRole role;
}
