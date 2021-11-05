package com.mo.service.pick;

import com.mo.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface PickService {
    @Transactional
    void patchPick(Long schoolIdx, User user);

    @Transactional(readOnly = true)
    Boolean checkIsPicked(Long schoolIdx, User user);
}
