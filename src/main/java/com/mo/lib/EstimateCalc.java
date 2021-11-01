package com.mo.lib;

import com.mo.domain.dto.estimate.data.EstimateDataDto;
import com.mo.domain.entity.Estimate;
import com.mo.domain.entity.School;
import com.mo.domain.repository.SchoolRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Component
public class EstimateCalc {

    private final SchoolRepo schoolRepo;

    public EstimateDataDto avgAndNumber(Long schoolIdx) {
        School school = schoolRepo.findById(schoolIdx).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "없는 학교입니다.")
        );

        double estimateSum = 0.0;
        int estimated = 0;
        for (Estimate estimate : school.getEstimates()) {
            estimateSum += estimate.getEstimateScore();
            estimated++;
        }

        Double avg = estimateSum / estimated;

        return new EstimateDataDto(avg, estimated);
    }
}
