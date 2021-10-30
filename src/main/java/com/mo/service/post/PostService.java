package com.mo.service.post;

import com.mo.domain.dto.post.StorePostDto;
import com.mo.domain.entity.User;

public interface PostService {
    void storePost(StorePostDto storePostDto, User user);
}
