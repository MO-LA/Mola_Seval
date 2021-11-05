package com.mo.controller;

import com.mo.domain.entity.User;
import com.mo.domain.response.Response;
import com.mo.service.pick.PickService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/pick")
@RestController
public class PickController {

    private final PickService pickService;

    @ApiOperation("별점 수정/저장")
    @PatchMapping
    public Response patchPick(@RequestParam Long schoolIdx,
                              HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        pickService.patchPick(schoolIdx, user);

        return new Response(HttpStatus.OK.value(), "성공");
    }

}
