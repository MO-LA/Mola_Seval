package com.mo.service.estimate;

import com.mo.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface EstimateService {
    @Transactional
    void patchEstimate(int score, Long schoolIdx, User user);
}
