package com.mo.domain.dto.estimate.data;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EstimateDataDto {
    private Double estimateAvg;
    private int estimated;

    public EstimateDataDto(Double estimateAvg, int estimated) {
        this.estimateAvg = estimateAvg;
        this.estimated = estimated;
    }
}
