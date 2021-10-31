package com.mo.service.review;

import com.mo.domain.dto.review.req.StoreReviewDto;
import com.mo.domain.dto.review.res.ReviewListRes;
import com.mo.domain.entity.Review;
import com.mo.domain.entity.School;
import com.mo.domain.entity.User;
import com.mo.domain.repository.ReviewRepo;
import com.mo.domain.repository.SchoolRepo;
import com.mo.domain.repository.page.ReviewPageRepo;
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
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final ReviewPageRepo reviewPageRepo;
    private final SchoolRepo schoolRepo;

    @Override
    @Transactional
    public void storeReview(StoreReviewDto storeReviewDto, User user) {
        School school = schoolRepo.findById(storeReviewDto.getSchoolIdx()).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "없는 학교입니다.")
        );
        Review review = storeReviewDto.toEntity(user, school);

        school.getReviews().add(review);

        reviewRepo.save(review);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewListRes> getReviewList(Long schoolIdx, Pageable pageable) {
        List<ReviewListRes> result = new ArrayList<>();

        School school = schoolRepo.findById(schoolIdx).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "없는 학교입니다.")
        );
        Page<Review> reviewPage = reviewPageRepo.findBySchool(pageable, school);
        List<Review> reviewList = reviewPage.toList();

        for (Review review : reviewList) {
            ReviewListRes res = new ReviewListRes(review);

            result.add(res);
        }

        return result;

    }
}
