package com.mo.service.review;

import com.mo.domain.dto.review.req.StoreReviewDto;
import com.mo.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewService {
    @Transactional
    void storeReview(StoreReviewDto storeReviewDto, User user);
}
