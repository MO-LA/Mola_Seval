package com.mo.service.comment;

import com.mo.domain.dto.comment.req.StoreCommentDto;
import com.mo.domain.entity.Comment;
import com.mo.domain.entity.Post;
import com.mo.domain.entity.User;
import com.mo.domain.repository.CommentRepo;
import com.mo.domain.repository.PostRepo;
import com.mo.domain.repository.page.CommentPageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepo commentRepo;
    private final CommentPageRepo commentPageRepo;
    private final PostRepo postRepo;

    @Override
    @Transactional
    public void storeComment(StoreCommentDto storeCommentDto, User user) {
        Post post = postRepo.findById(storeCommentDto.getAlbumIdx()).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "없는 게시글입니다.")
        );
        Comment comment = storeCommentDto.toEntity(user, post);
        commentRepo.save(comment);
    }
}
