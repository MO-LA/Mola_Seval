package com.mo.service.review;

import com.mo.domain.dto.review.req.StoreReviewDto;
import com.mo.domain.entity.Review;
import com.mo.domain.entity.School;
import com.mo.domain.entity.User;
import com.mo.domain.repository.ReviewRepo;
import com.mo.domain.repository.SchoolRepo;
import com.mo.domain.repository.UserRepo;
import com.mo.domain.repository.page.ReviewPageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final ReviewPageRepo reviewPageRepo;
    private final UserRepo userRepo;
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
}
