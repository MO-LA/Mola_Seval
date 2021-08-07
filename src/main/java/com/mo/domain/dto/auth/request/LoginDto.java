package com.mo.domain.dto.auth.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDto {
    @NotBlank
    private String id;

    @NotBlank
    private String password;
}
