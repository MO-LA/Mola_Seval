package com.mo.domain.dto.post.req;

import com.mo.domain.entity.Post;
import com.mo.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StorePostDto {
    String content;

    public Post toEntity(User user) {
        return Post.builder()
                .content(content)
                .author(user)
                .build();
    }
}
