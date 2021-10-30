package com.mo.controller;

import com.mo.domain.dto.post.StorePostDto;
import com.mo.domain.entity.User;
import com.mo.domain.response.Response;
import com.mo.service.post.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class PostController {

    private final PostService postService;

    @ApiOperation("게시글 저장")
    @PostMapping
    public Response storePost(@RequestBody StorePostDto storePostDto,
                              HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        postService.storePost(storePostDto, user);

        Response response = new Response();

        response.setStatus(HttpStatus.OK.value());
        response.setMassage("성공");

        return response;
    }
}
