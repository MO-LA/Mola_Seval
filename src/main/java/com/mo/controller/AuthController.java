package com.mo.controller;

import com.mo.domain.dto.auth.request.IdDVDto;
import com.mo.domain.dto.auth.request.LoginDto;
import com.mo.domain.dto.auth.request.RegisterDto;
import com.mo.domain.dto.auth.res.LoginResDto;
import com.mo.domain.response.Response;
import com.mo.domain.response.ResponseData;
import com.mo.service.auth.AuthService;
import io.swagger.annotations.ApiOperation;
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

    private final Response response = new Response();

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
    public ResponseData<LoginResDto> login(@RequestBody @Valid LoginDto loginDto) {

        LoginResDto data = authService.login(loginDto);

        ResponseData<LoginResDto> responseData = new ResponseData<>();

        responseData.setStatus(HttpStatus.OK.value());
        responseData.setMassage("로그인 성공");
        responseData.setData(data);

        return responseData;
    }

    /**
     * id 중복체크
     * @return
     */
    @ApiOperation("id 중복체크")
    @PostMapping("/DV")
    public ResponseData<Boolean> idDuplicateVerification(@RequestBody IdDVDto idDVDto) {
        Boolean massage = authService.IdDuplicateVerification(idDVDto.getId());

        ResponseData<Boolean> responseData = new ResponseData<>();

        responseData.setMassage("중복체크 성공");
        responseData.setStatus(HttpStatus.OK.value());
        responseData.setData(massage);

        return responseData;
    }
}
