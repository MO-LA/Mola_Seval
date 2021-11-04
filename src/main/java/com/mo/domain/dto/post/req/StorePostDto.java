package com.mo.domain.dto.post.req;

import com.mo.domain.entity.Post;
import com.mo.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class StorePostDto {
    @NotBlank
    String title;
    @NotBlank
    String content;

    public Post toEntity(User user) {
        return Post.builder()
                .title(title)
                .content(content)
                .author(user)
                .build();
    }
}
