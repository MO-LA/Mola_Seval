package com.mo.domain.dto.auth.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class LoginResDto {
    @JsonProperty
    private String accessToken;
    @JsonProperty
    private String refreshToken;

    public LoginResDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
