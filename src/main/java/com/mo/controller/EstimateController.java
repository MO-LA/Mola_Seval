package com.mo.controller;

import com.mo.domain.entity.User;
import com.mo.domain.response.Response;
import com.mo.service.estimate.EstimateService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/estimate")
@RestController
public class EstimateController {

    private final EstimateService estimateService;

    @ApiOperation("별점 저장")
    @PatchMapping
    public Response patchEstimate(@RequestParam int estimate,
                                  @RequestParam Long schoolIdx,
                                  HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        estimateService.petchEstimate(estimate, schoolIdx, user);

        return new Response(HttpStatus.OK.value(), "성공");
    }
}
