package com.mo.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData<T> extends Response{
    private T data;
}
