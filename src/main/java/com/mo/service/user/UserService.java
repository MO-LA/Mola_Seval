package com.mo.service.user;

import com.mo.domain.dto.school.res.SchoolListRes;
import com.mo.domain.dto.user.res.SimpleUserInfoRes;
import com.mo.domain.dto.user.res.UserInfoRes;
import com.mo.domain.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional(readOnly = true)
    SimpleUserInfoRes getSimpleUserInfo(User user);

    @Transactional(readOnly = true)
    UserInfoRes getUserInfo(User user);

    @Transactional(readOnly = true)
    UserInfoRes getOtherUserInfo(Long userIdx);

    @Transactional(readOnly = true)
    List<SchoolListRes> getPickedSchools(User reqUser);
}
