package com.mo.domain.repository.page;

import com.mo.domain.entity.Review;
import com.mo.domain.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ReviewPageRepo extends PagingAndSortingRepository<Review, Long> {
    Page<Review> findBySchool(Pageable pageable, School school);
}
