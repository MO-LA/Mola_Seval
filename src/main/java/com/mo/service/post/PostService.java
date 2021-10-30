package com.mo.service.post;

import com.mo.domain.dto.post.req.StorePostDto;
import com.mo.domain.dto.post.res.PostListRes;
import com.mo.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {
    void storePost(StorePostDto storePostDto, User user);

    @Transactional(readOnly = true)
    List<PostListRes> getPostList(Pageable pageable);
}
