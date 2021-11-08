package com.mo.domain.dto.post.res;

import com.mo.domain.dto.user.res.SimpleUserInfoRes;
import com.mo.domain.entity.Post;
import com.mo.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter @Setter
public class PostListRes extends SimpleUserInfoRes {
    private String dateTime;

    private String title;

    private String content;

    private Long idx;

    public PostListRes() {
    }

    public PostListRes(User user, Post post) {
        super(user);
        this.dateTime = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분"));
        this.title = post.getTitle();
        this.content = post.getContent();
        this.idx = post.getIdx();
    }
}
