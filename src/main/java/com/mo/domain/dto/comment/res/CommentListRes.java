package com.mo.domain.dto.comment.res;

import com.mo.domain.dto.user.res.SimpleUserInfoRes;
import com.mo.domain.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentListRes extends SimpleUserInfoRes {
    private String content;
    private Long idx;

    public CommentListRes(Comment comment) {
        super(comment.getAuthor());
        this.content = comment.getContent();
        this.idx = comment.getIdx();
    }
}
