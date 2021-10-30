package com.mo.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData<T> extends Response{
    private T data;

    public ResponseData() {}

    public ResponseData(T data) {
        this.data = data;
    }

    public ResponseData(int status, String massage, T data) {
        super(status, massage);
        this.data = data;
    }
}
