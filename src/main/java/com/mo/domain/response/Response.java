package com.mo.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private int status;
    private String massage;

    public Response() {
    }

    public Response(int status, String massage) {
        this.status = status;
        this.massage = massage;
    }

}
