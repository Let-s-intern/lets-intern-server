package com.letsintern.letsintern.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Token {

    @NotNull
    private String accessToken;

    @NotNull
    private String refreshToken;

    @Builder
    private Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static Token from(String accessToken, String refreshToken) {
        return Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
