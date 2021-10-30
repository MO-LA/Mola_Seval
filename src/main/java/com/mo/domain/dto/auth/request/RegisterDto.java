package com.mo.domain.dto.auth.request;

import com.mo.domain.entity.User;
import com.mo.enums.user.Sex;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterDto {
    @NotBlank
    private String id;

    @NotBlank
    private String password;

    private Sex sex = null;

    private Integer age = null;

    private String residentialArea = null;

    private String school = null;

    public User toEntity() {
        return User.builder()
                .id(id)
                .password(password)
                .sex(sex)
                .age(age)
                .residentialArea(residentialArea)
                .school(school)
                .build();
    }
}
