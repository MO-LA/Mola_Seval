package com.mo.domain.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseData<T> extends Response{
    private T data;
}
