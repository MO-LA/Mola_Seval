package com.mo.service.review;

import com.mo.domain.dto.review.req.StoreReviewDto;
import com.mo.domain.dto.review.res.ReviewListRes;
import com.mo.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewService {
    @Transactional
    void storeReview(StoreReviewDto storeReviewDto, User user);

    @Transactional(readOnly = true)
    List<ReviewListRes> getReviewList(Long schoolIdx, Pageable pageable);
}
