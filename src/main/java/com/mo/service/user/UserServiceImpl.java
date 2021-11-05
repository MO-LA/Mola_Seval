package com.mo.service.user;

import com.mo.domain.dto.user.res.SimpleUserInfoRes;
import com.mo.domain.dto.user.res.UserInfoRes;
import com.mo.domain.entity.User;
import com.mo.domain.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    @Transactional(readOnly = true)
    public SimpleUserInfoRes getSimpleUserInfo(User user) {
        return new SimpleUserInfoRes(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoRes getUserInfo(User user) {
        return new UserInfoRes(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoRes getOtherUserInfo(Long userIdx) {
        User user = userRepo.findById(userIdx).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, "없는 유저입니다")
        );
        return new UserInfoRes(user);
    }

}
