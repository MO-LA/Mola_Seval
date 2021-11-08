package com.mo.service.user;

import com.mo.domain.dto.school.res.SchoolListRes;
import com.mo.domain.dto.user.res.SimpleUserInfoRes;
import com.mo.domain.dto.user.res.UserInfoRes;
import com.mo.domain.entity.Pick;
import com.mo.domain.entity.School;
import com.mo.domain.entity.User;
import com.mo.domain.repository.UserRepo;
import com.mo.lib.EstimateCalc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final EstimateCalc estimateCalc;

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

    @Override
    @Transactional(readOnly = true)
    public List<SchoolListRes> getPickedSchools(User reqUser) {
        List<SchoolListRes> result = new ArrayList<>();
        User user = userRepo.findById(reqUser.getIdx()).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "없는 사용자입니다")
        );
        for (Pick pick : user.getPicks()) {
            if (Boolean.FALSE.equals(pick.getState())) {
                continue;
            }
            School school = pick.getSchool();
            result.add(new SchoolListRes(school, estimateCalc.avgAndNumber(school)));
        }

        return result;
    }

}
