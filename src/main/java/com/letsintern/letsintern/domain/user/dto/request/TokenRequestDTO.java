package com.letsintern.letsintern.domain.user.dto.request;

import lombok.Getter;

@Getter
public class TokenRequestDTO {

    private String accessToken;
    private String refreshToken;
}
