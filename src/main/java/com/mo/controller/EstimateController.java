package com.mo.controller;

import com.mo.domain.entity.User;
import com.mo.domain.response.Response;
import com.mo.domain.response.ResponseData;
import com.mo.service.estimate.EstimateService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        estimateService.patchEstimate(estimate, schoolIdx, user);

        return new Response(HttpStatus.OK.value(), "성공");
    }

    @ApiOperation("별점 평균")
    @GetMapping("/avg")
    public ResponseData<Double> getAvg(Long schoolIdx) {
        Double data = estimateService.estimateScoreAvg(schoolIdx);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }

    @ApiOperation("해당 학교에 내가 한 별점 불러오기")
    @GetMapping("/{schoolIdx}")
    public ResponseData<Integer> getMyEstimate(@PathVariable Long schoolIdx,
                                               HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        int data = estimateService.myEstimate(schoolIdx, user);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }
}
