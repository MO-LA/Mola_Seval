package com.mo.service.comment;

import com.mo.domain.dto.comment.req.StoreCommentDto;
import com.mo.domain.dto.comment.res.CommentListRes;
import com.mo.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentService {
    @Transactional
    void storeComment(StoreCommentDto storeCommentDto, User user);

    @Transactional(readOnly = true)
    List<CommentListRes> getCommentList(Pageable pageable, Long postIdx);
}
