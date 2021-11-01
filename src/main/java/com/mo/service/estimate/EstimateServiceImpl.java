package com.mo.service.estimate;

import com.mo.domain.entity.Estimate;
import com.mo.domain.entity.School;
import com.mo.domain.entity.User;
import com.mo.domain.repository.EstimateRepo;
import com.mo.domain.repository.SchoolRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Service
public class EstimateServiceImpl implements EstimateService {

    private final SchoolRepo schoolRepo;
    private final EstimateRepo estimateRepo;

    @Override
    @Transactional
    public void petchEstimate(int score, Long schoolIdx, User user) {
        School school = schoolRepo.findById(schoolIdx).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "없는 학교입니다.")
        );
        Estimate estimate = estimateRepo.findBySchoolAndUser(school, user).orElse(
                new Estimate(user, school)
        );

        estimate.setEstimateScore(score);
        school.getEstimates().add(estimate);
    }
}