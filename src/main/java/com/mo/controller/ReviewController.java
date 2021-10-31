package com.mo.controller;

import com.mo.domain.dto.review.req.StoreReviewDto;
import com.mo.domain.dto.review.res.ReviewListRes;
import com.mo.domain.entity.User;
import com.mo.domain.response.Response;
import com.mo.domain.response.ResponseData;
import com.mo.service.review.ReviewService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/review")
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @ApiOperation("리뷰 저장")
    @PostMapping
    public Response storeReview (@RequestBody StoreReviewDto storeReviewDto,
                                HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        reviewService.storeReview(storeReviewDto, user);

        return new Response(HttpStatus.OK.value(), "성공");
    }

    @ApiOperation("리뷰 리스트")
    @GetMapping("/list")
    public ResponseData<List<ReviewListRes>> getReviewList(Pageable pageable, @RequestParam Long schoolIdx) {
        List<ReviewListRes> data = reviewService.getReviewList(schoolIdx, pageable);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }
}
