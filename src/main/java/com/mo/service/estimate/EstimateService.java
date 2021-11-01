package com.mo.service.estimate;

import com.mo.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface EstimateService {
    @Transactional
    void patchEstimate(int score, Long schoolIdx, User user);

    @Transactional(readOnly = true)
    Double estimateScoreAvg(Long schoolIdx);

    @Transactional(readOnly = true)
    int myEstimate(Long schoolIdx, User user);
}
