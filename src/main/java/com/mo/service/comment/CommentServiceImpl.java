package com.mo.service.comment;

import com.mo.domain.dto.comment.req.StoreCommentDto;
import com.mo.domain.dto.comment.res.CommentListRes;
import com.mo.domain.entity.Comment;
import com.mo.domain.entity.Post;
import com.mo.domain.entity.User;
import com.mo.domain.repository.CommentRepo;
import com.mo.domain.repository.PostRepo;
import com.mo.domain.repository.page.CommentPageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public List<CommentListRes> getCommentList(Pageable pageable, Long postIdx) {
        List<CommentListRes> result = new ArrayList<>();

        Post post = postRepo.findById(postIdx).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "없는 게시글입니다.")
        );
        Page<Comment> commentPage = commentPageRepo.findAllByPost(pageable, post);
        List<Comment> commentList = commentPage.toList();

        for (Comment comment : commentList) {
            CommentListRes res = new CommentListRes(comment);

            result.add(res);
        }

        return result;
    }
}
