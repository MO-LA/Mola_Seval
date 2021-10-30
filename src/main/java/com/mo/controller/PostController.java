package com.mo.controller;

import com.mo.domain.dto.post.req.StorePostDto;
import com.mo.domain.dto.post.res.PostListRes;
import com.mo.domain.entity.User;
import com.mo.domain.response.Response;
import com.mo.domain.response.ResponseData;
import com.mo.service.post.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @ApiOperation(value = "게시글 리스트 불러오기",
            notes = "query 에서 [page = 가져올 페이지 (default = 0)] " +
                    "[size = 가져올 크기 (default = 20) " +
                    "[sort = 걍 쓰지마 궁금하면 나중에 알려줄게]" +
                    "리스트 불러오기는 다 이런식일거니까 궁금하면 여기 설명 읽어 안적어놓을거야")
    @GetMapping("/list")
    public ResponseData<List<PostListRes>> getPostList(Pageable pageable) {
        List<PostListRes> data = postService.getPostList(pageable);

        return new ResponseData<>(HttpStatus.OK.value(), "성공", data);
    }
}
