package com.mo.lib;

import com.mo.domain.dto.estimate.data.EstimateDataDto;
import com.mo.domain.entity.Estimate;
import com.mo.domain.entity.School;
import com.mo.domain.repository.SchoolRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EstimateCalc {

    private final SchoolRepo schoolRepo;

    public EstimateDataDto avgAndNumber(School school) {

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
