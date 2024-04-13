package com.letsintern.letsintern.domain.program.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ZoomAuthResponse {

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "token_type")
    private String tokenType;

    @JsonProperty(value = "expires_in")
    private Long expiresIn;

    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

}
