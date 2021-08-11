package com.mo.controller;

import com.mo.domain.response.Response;
import com.mo.domain.response.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/test")
@RestController
public class TestController {

    @PostMapping("")
    public ResponseData<String> postTest(@RequestBody String massage) {
        ResponseData response = new ResponseData();

        response.setStatus(HttpStatus.OK.value());
        response.setMassage("됨");
        response.setData(massage + " 이거 맞지?");

        return response;
    }

    @GetMapping("")
    public Response getTest() {
        Response response = new Response();

        response.setStatus(HttpStatus.OK.value());
        response.setMassage("됨");

        return response;
    }
}
