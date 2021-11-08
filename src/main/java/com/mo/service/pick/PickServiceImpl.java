package com.mo.service.pick;

import com.mo.domain.entity.Pick;
import com.mo.domain.entity.School;
import com.mo.domain.entity.User;
import com.mo.domain.repository.PickRepo;
import com.mo.domain.repository.SchoolRepo;
import com.mo.domain.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Service
public class PickServiceImpl implements PickService {

    private final PickRepo pickRepo;
    private final SchoolRepo schoolRepo;
    private final UserRepo userRepo;

    @Override
    @Transactional
    public void patchPick(Long schoolIdx, User reqUser) {
        try {
            School school = schoolRepo.findById(schoolIdx).orElseThrow(
                    () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "없는 학교입니다.")
            );
            User user = userRepo.findById(reqUser.getIdx()).orElseThrow(
                    () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "없는 사용자입니다")
            );
            Pick pick = pickRepo.findBySchoolAndUser(school, user).orElse(
                    new Pick(false, school, user)
            );

            pick.setState(!pick.getState());
            Pick savedPick = pickRepo.save(pick);
            user.getPicks().add(savedPick);
            school.getPicks().add(savedPick);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean checkIsPicked(Long schoolIdx, User user) {
        School school = schoolRepo.findById(schoolIdx).orElseThrow(
                () -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "없는 학교입니다.")
        );
        Pick pick = pickRepo.findBySchoolAndUser(school, user).orElse(
                new Pick(false, school, user)
        );

        return pick.getState();
    }
}
