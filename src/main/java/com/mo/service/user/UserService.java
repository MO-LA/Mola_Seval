package com.mo.service.user;

import com.mo.domain.dto.user.res.SimpleUserInfoRes;
import com.mo.domain.dto.user.res.UserInfoRes;
import com.mo.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    @Transactional(readOnly = true)
    SimpleUserInfoRes getSimpleUserInfo(User user);

    @Transactional(readOnly = true)
    UserInfoRes getUserInfo(User user);

    @Transactional(readOnly = true)
    UserInfoRes getOtherUserInfo(Long userIdx);
}
