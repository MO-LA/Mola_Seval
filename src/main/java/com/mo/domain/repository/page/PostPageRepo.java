package com.mo.domain.repository.page;

import com.mo.domain.entity.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostPageRepo extends PagingAndSortingRepository<Post, Long> {
}
