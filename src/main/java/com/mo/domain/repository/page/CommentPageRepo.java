package com.mo.domain.repository.page;

import com.mo.domain.entity.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentPageRepo extends PagingAndSortingRepository<Comment, Long> {
}
