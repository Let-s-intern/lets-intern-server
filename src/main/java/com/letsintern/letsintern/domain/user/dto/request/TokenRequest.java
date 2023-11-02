package com.letsintern.letsintern.domain.user.dto.request;

import lombok.Getter;

@Getter
public class TokenRequest {

    private String accessToken;
    private String refreshToken;
}
