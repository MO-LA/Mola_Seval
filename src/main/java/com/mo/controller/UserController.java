package com.mo.controller;

import com.mo.domain.dto.user.res.SimpleUserInfoRes;
import com.mo.domain.entity.User;
import com.mo.domain.response.ResponseData;
import com.mo.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @ApiOperation("아이디이랑 학교")
    @GetMapping
    public ResponseData<SimpleUserInfoRes> getUserInfo(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        SimpleUserInfoRes data = userService.getSimpleUserInfo(user);
        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }
}
