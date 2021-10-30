package com.mo.controller;

import com.mo.domain.dto.comment.req.StoreCommentDto;
import com.mo.domain.entity.User;
import com.mo.domain.response.Response;
import com.mo.service.comment.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
}
