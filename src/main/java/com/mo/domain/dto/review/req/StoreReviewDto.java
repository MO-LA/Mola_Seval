package com.mo.domain.dto.review.req;

import com.mo.domain.entity.Review;
import com.mo.domain.entity.School;
import com.mo.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StoreReviewDto {
    private String content;
    private Long schoolIdx;

    public Review toEntity(User user, School school) {
        return Review.builder()
                .author(user)
                .school(school)
                .content(content)
                .build();
    }
}
