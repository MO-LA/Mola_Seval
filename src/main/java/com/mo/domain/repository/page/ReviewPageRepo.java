package com.mo.domain.repository.page;

import com.mo.domain.entity.Review;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReviewPageRepo extends PagingAndSortingRepository<Review, Long> {
}
