package com.mo.service.user;

import com.mo.domain.dto.user.res.SimpleUserInfoRes;
import com.mo.domain.entity.User;
import com.mo.domain.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    @Transactional(readOnly = true)
    public SimpleUserInfoRes getSimpleUserInfo(User user) {
        return new SimpleUserInfoRes(user);
    }

}
