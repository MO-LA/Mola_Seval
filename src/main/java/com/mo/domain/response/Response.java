package com.mo.domain.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class Response {
    private HttpStatus status;
    private String massage;
}
