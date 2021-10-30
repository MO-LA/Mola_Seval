package com.mo.controller;

import com.mo.domain.dto.comment.req.StoreCommentDto;
import com.mo.domain.dto.comment.res.CommentListRes;
import com.mo.domain.entity.User;
import com.mo.domain.response.Response;
import com.mo.domain.response.ResponseData;
import com.mo.service.comment.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentController {

    private final CommentService commentService;

    @ApiOperation("댓글 저장")
    @PostMapping
    public Response storeComment(@RequestBody StoreCommentDto storeCommentDto,
                                 HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        commentService.storeComment(storeCommentDto, user);

        return new Response(HttpStatus.OK.value(), "성공");
    }

    @ApiOperation("리스트")
    @GetMapping("/list")
    public ResponseData<List<CommentListRes>> getCommentLIst(Pageable pageable, @RequestParam Long postIdx) {
        List<CommentListRes> data = commentService.getCommentList(pageable, postIdx);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }
}
