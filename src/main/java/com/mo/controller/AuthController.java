package com.mo.controller;

import com.mo.domain.dto.auth.request.RegisterDto;
import com.mo.domain.response.Response;
import com.mo.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("")
    public Response register(@RequestBody @Valid RegisterDto registerDto) {
        authService.register(registerDto);

        Response response = new Response();

        response.setStatus(HttpStatus.OK);
        response.setMassage("가입 성공");

        return response;
    }
}
