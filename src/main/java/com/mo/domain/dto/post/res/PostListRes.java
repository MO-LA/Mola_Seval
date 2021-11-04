package com.mo.domain.dto.post.res;

import com.mo.domain.dto.user.res.SimpleUserInfoRes;
import com.mo.domain.entity.Post;
import com.mo.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PostListRes extends SimpleUserInfoRes {
    private LocalDateTime dateTime;

    private String title;

    private String content;

    private Long idx;

    public PostListRes() {
    }

    public PostListRes(User user, Post post) {
        super(user);
        this.dateTime = post.getDate();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.idx = post.getIdx();
    }
}
