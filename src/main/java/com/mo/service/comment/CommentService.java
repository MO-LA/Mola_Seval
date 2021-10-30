package com.mo.service.comment;

import com.mo.domain.dto.comment.req.StoreCommentDto;
import com.mo.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface CommentService {
    @Transactional
    void storeComment(StoreCommentDto storeCommentDto, User user);
}
