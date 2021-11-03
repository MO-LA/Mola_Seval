package com.mo.service.user;

import com.mo.domain.dto.user.res.SimpleUserInfoRes;
import com.mo.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    @Transactional(readOnly = true)
    SimpleUserInfoRes getSimpleUserInfo(User user);
}
