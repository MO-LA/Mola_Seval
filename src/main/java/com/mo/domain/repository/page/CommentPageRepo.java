package com.mo.domain.repository.page;

import com.mo.domain.entity.Comment;
import com.mo.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentPageRepo extends PagingAndSortingRepository<Comment, Long> {
    Page<Comment> findAllByPost(Pageable pageable, Post post);
}
