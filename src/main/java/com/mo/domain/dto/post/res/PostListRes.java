package com.mo.domain.dto.post.res;

import com.mo.domain.dto.user.res.SimpleUserInfoRes;
import com.mo.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PostListRes extends SimpleUserInfoRes {
    private LocalDateTime dateTime;

    private String content;

    public PostListRes() {
    }

    public PostListRes(User user, LocalDateTime dateTime, String content) {
        super(user);
        this.dateTime = dateTime;
        this.content = content;
    }
}
