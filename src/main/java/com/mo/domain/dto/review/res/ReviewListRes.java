package com.mo.domain.dto.review.res;

import com.mo.domain.dto.user.res.SimpleUserInfoRes;
import com.mo.domain.entity.Review;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewListRes extends SimpleUserInfoRes {
    private String content;

    public ReviewListRes(Review review) {
        super(review.getAuthor());
        this.content = review.getContent();
    }
}
