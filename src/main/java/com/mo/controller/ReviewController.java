package com.mo.controller;

import com.mo.domain.dto.review.req.StoreReviewDto;
import com.mo.domain.entity.User;
import com.mo.domain.response.Response;
import com.mo.service.review.ReviewService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/review")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation("리뷰 저장")
    @PostMapping
    public Response storeReview(@RequestBody StoreReviewDto storeReviewDto,
                                HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        reviewService.storeReview(storeReviewDto, user);

        return new Response(HttpStatus.OK.value(), "성공");
    }
}
