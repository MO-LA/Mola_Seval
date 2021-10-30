package com.mo.domain.dto.comment.req;

import com.mo.domain.entity.Comment;
import com.mo.domain.entity.Post;
import com.mo.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StoreCommentDto {
    private String content;
    private Long albumIdx;

    public Comment toEntity(User user, Post post) {
        return Comment.builder()
                .post(post)
                .author(user)
                .content(content)
                .build();
    }
}
