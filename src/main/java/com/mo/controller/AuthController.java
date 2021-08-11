package com.mo.controller;

import com.mo.domain.dto.auth.request.IdDVDto;
import com.mo.domain.dto.auth.request.LoginDto;
import com.mo.domain.dto.auth.request.RegisterDto;
import com.mo.domain.response.Response;
import com.mo.domain.response.ResponseData;
import com.mo.service.auth.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final Response response = new Response();
    private final ResponseData responseData = new ResponseData();

    @ApiOperation("회원가입")
    @PostMapping("")
    public Response register(@RequestBody @Valid RegisterDto registerDto) {
        authService.register(registerDto);

        response.setStatus(HttpStatus.OK.value());
        response.setMassage("가입 성공");

        return response;
    }

    @ApiOperation("로그인")
    @PostMapping("/login")
    public Response login(@RequestBody @Valid LoginDto loginDto) {
        try {
            if(authService.login(loginDto)) {
                response.setStatus(HttpStatus.OK.value());
                response.setMassage("로그인 성공");
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setMassage("로그인 실패");
            }
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.OK.value());
            response.setMassage("로그인 실패");
        }

        return response;
    }

    /**
     * id 중복체크
     */
    @ApiOperation("id 중복체크")
    @PostMapping("/DV")
    public ResponseData<String> idDuplicateVerification(@RequestBody IdDVDto idDVDto) {
        String massage = authService.IdDuplicateVerification(idDVDto.getId());

        responseData.setMassage("중복체크 성공");
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setData(massage);

        return responseData;
    }
}
