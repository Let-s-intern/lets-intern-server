package com.letsintern.letsintern.domain.user.dto.request;

import lombok.Getter;

@Getter
public class PwChangeDTO {

    private String currentPassword;
    private String newPassword;
}
